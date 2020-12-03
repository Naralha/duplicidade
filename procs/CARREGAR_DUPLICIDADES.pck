create or replace package CARREGAR_DUPLICIDADES is

  -- Author  : ACAMPOS
  -- Created : 11/11/2020
  -- Purpose : 

  procedure processarRecorteLinhas;
  procedure processarimportacao;

end CARREGAR_DUPLICIDADES;
/
create or replace package body CARREGAR_DUPLICIDADES is
  gSYSDATE DATE;
  v_code   number;
  v_errm   VARCHAR2(100);
  
  /* Dominios
  Coluna tipo_movimento - tabela movimento
  CC- CONFIRMA CONTROLE
  DH - DEFINE HOMONIMA
  NC - N?O CONTROLO
  SD - SOLICITAR DOCUMENTO
  SR - SOLICITAR REFERENCIA
  C  - COMENTARIOS
  EA - EMAIL ANEXO
  -----------
  */
  
 

 PROCEDURE truncartabelasinterface IS
  BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_OBRA_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_TITULAR_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_AUT_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_DEP_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_HOM_TEMP';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.DUPLICIDADE_AGRUPADOR_TEMP';
  END;
  
  PROCEDURE processarrecortelinhas IS
    vtipolinha    char;
    vduplicidade  DB_DUPLICIDADE.DUPLICIDADE_TEMP%rowtype := NULL;
    vobra         DB_DUPLICIDADE.DUPLICIDADE_OBRA_TEMP%rowtype := NULL;
    vtitular      DB_DUPLICIDADE.DUPLICIDADE_TITULAR_TEMP%rowtype := NULL;
    vresolvida    DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_DEP_TEMP%rowtype := NULL;
    vhomonima     DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_HOM_TEMP%rowtype := NULL;
    vresolvidaaut DB_DUPLICIDADE.DUPLICIDADE_RESOLVIDA_AUT_TEMP%rowtype := NULL;
    vagrupador    DB_DUPLICIDADE.DUPLICIDADE_AGRUPADOR_TEMP%rowtype := NULL;
  
    FUNCTION recortadate(a_texto   VARCHAR2,
                         a_inicio  INTEGER,
                         a_tamanho INTEGER) RETURN DATE IS
      vdatarecortada VARCHAR2(8);
    BEGIN
      vdatarecortada := substr(a_texto, a_inicio, a_tamanho);
      IF vdatarecortada IS NULL THEN
        RETURN NULL;
      ELSE
        RETURN TO_DATE(vdatarecortada, 'YYYYMMDD');
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        RETURN NULL;
    END;
  
  BEGIN
    truncartabelasinterface;
    FOR r_txt IN (SELECT adu.linha, adu.texto
                    FROM arquivo_duplicidade adu
                   ORDER BY adu.linha) LOOP
      vtipolinha := substr(r_txt.texto, 8, 1);
      IF vtipolinha = '1' THEN
        vduplicidade                         := NULL;
        vobra                                := NULL;
        vtitular                             := NULL;
        vduplicidade.cd_sociedade_ecad       := substr(r_txt.texto, 1, 3);
        vduplicidade.tipo_operacao           := substr(r_txt.texto, 4, 1);
        vduplicidade.tipo_cadastro           := substr(r_txt.texto, 5, 3);
        vduplicidade.tipo_registro           := substr(r_txt.texto, 8, 1);
        vduplicidade.cd_duplicidade          := substr(r_txt.texto, 9, 9);
        vduplicidade.dt_referencia           := recortadate(r_txt.texto,
                                                            18,
                                                            8);
        vduplicidade.motivo_bloqueio         := substr(
                                                       r_txt.texto,
                                                       26,
                                                       3);
        vduplicidade.cd_agrupador            := substr(
                                                       r_txt.texto,
                                                       29,
                                                       15);
        INSERT INTO DUPLICIDADE_TEMP VALUES vduplicidade;
      
      ELSIF vtipolinha = '2' THEN
        vobra                                := NULL;
        vtitular                             := NULL;
        vobra.cd_sociedade_ecad              := substr(r_txt.texto, 1, 3);
        vobra.tipo_operacao                  := substr(r_txt.texto, 4, 1);
        vobra.tipo_cadastro                  := substr(r_txt.texto, 5, 3);
        vobra.tipo_registro                  := substr(r_txt.texto, 8, 1);
        vobra.cd_duplicidade                 := substr(r_txt.texto, 9, 9);
        vobra.cd_ecad                        := substr(r_txt.texto, 18, 13);
        vobra.cd_societario                  := substr(r_txt.texto, 31, 15);
        vobra.titulo_principal               := substr(r_txt.texto, 46, 95);
        vobra.is_nacional                    := substr(r_txt.texto, 141, 1);
        vobra.is_derivada                    := substr(r_txt.texto, 142, 1);   
        vobra.cd_agrupador                   := substr(r_txt.texto, 143, 15);
        vobra.soc_resp_info                  := substr(r_txt.texto, 158, 22);
        vobra.soc_resp_cad_orig              := substr(r_txt.texto, 180, 22);
        
        INSERT INTO DUPLICIDADE_OBRA_TEMP VALUES vobra;
      
      ELSIF vtipolinha = '3' THEN
        vtitular                         := NULL;
        vtitular.cd_sociedade_ecad       := substr(r_txt.texto, 1, 3);
        vtitular.tipo_operacao           := substr(r_txt.texto, 4, 1);
        vtitular.tipo_cadastro           := substr(r_txt.texto, 5, 3);
        vtitular.tipo_registro           := substr(r_txt.texto, 8, 1);
        vtitular.cd_duplicidade          := substr(r_txt.texto, 9, 9);
        vtitular.cd_ecad_obra            := substr(r_txt.texto, 18, 13);
        vtitular.cd_societario_obra      := substr(r_txt.texto, 31, 15);
        vtitular.cd_ecad_titular         := substr(r_txt.texto, 46, 13);
        vtitular.cd_societario_titular   := substr(r_txt.texto, 59, 15);
        vtitular.nm_titular              := substr(r_txt.texto, 74, 60);
        vtitular.pseudo_titular          := substr(r_txt.texto, 134, 60);
        vtitular.cd_cisac_sociedade      := substr(r_txt.texto, 194, 3);
        --dbms_output.put_line('CATEGORIA:'||substr(r_txt.texto, 197, 2));    
        vtitular.cd_categoria            := substr(r_txt.texto, 197, 2);
        vtitular.cd_sub_categoria        := substr(r_txt.texto, 199, 2);
        vtitular.dt_inicio_contrato      := recortadate(r_txt.texto, 201, 8);
        vtitular.dt_fim_contrato         := recortadate(r_txt.texto, 209, 8);
        vtitular.cd_link_ecad            := substr(r_txt.texto, 217, 3);
        vtitular.percent_partic_titular  := substr(r_txt.texto, 220, 5);
        vtitular.percent_partic_titular  := vtitular.percent_partic_titular/100;
        
        vtitular.cd_agrupador            := substr(r_txt.texto, 225, 15);
      
        -- Seta 99 quando codigo da sociedade for = a zeros,pois na tabela da Abramus
        -- 99 e igual a 'NS' - n?o associado      
        IF vtitular.cd_sociedade_ecad = '000' THEN
          vtitular.cd_sociedade_ecad := '099';
        END IF;
        
        
        INSERT INTO DUPLICIDADE_TITULAR_TEMP VALUES vtitular;


      ELSIF vtipolinha = '4' THEN
        --Duplicidade resolvida
        vresolvida                       := NULL;
        vresolvida.cd_sociedade_ecad     := substr(r_txt.texto, 1, 3);
        vresolvida.tipo_operacao         := substr(r_txt.texto, 4, 1);
        vresolvida.tipo_cadastro         := substr(r_txt.texto, 5, 3);
        vresolvida.tipo_registro         := substr(r_txt.texto, 8, 1);
        vresolvida.cd_duplicidade        := substr(r_txt.texto, 9, 9);
        vresolvida.cd_ecad_obra_de       := substr(r_txt.texto, 18, 13);
        vresolvida.cd_ecad_obra_para     := substr(r_txt.texto, 31, 13);
        vresolvida.dt_referencia         := recortadate(r_txt.texto, 44, 8);
        vresolvida.cd_agrupador          := substr(r_txt.texto, 52, 15);
        
        INSERT INTO DUPLICIDADE_RESOLVIDA_DEP_TEMP VALUES vresolvida;

      
      ELSIF vtipolinha = '5' THEN
        --Duplicidade homonima
        vhomonima                       := NULL;
        vhomonima.cd_sociedade_ecad     := substr(r_txt.texto, 1, 3);
        vhomonima.tipo_operacao         := substr(r_txt.texto, 4, 1);
        vhomonima.tipo_cadastro         := substr(r_txt.texto, 5, 3);
        vhomonima.tipo_registro         := substr(r_txt.texto, 8, 1);
        vhomonima.cd_duplicidade        := substr(r_txt.texto, 9, 9);
        vhomonima.cd_ecad_obra          := substr(r_txt.texto, 18, 13);
        vhomonima.dt_referencia         := recortadate(r_txt.texto, 31, 8);
        vhomonima.cd_agrupador          := substr(r_txt.texto, 39, 15);
        
        INSERT INTO DUPLICIDADE_RESOLVIDA_HOM_TEMP VALUES vhomonima;

      
      ELSIF vtipolinha = '6' THEN
        vresolvidaaut                  := NULL;
        vresolvidaaut.cd_sociedade_ecad     := substr(r_txt.texto, 1, 3);
        vresolvidaaut.tipo_operacao         := substr(r_txt.texto, 4, 1);
        vresolvidaaut.tipo_cadastro         := substr(r_txt.texto, 5, 3);
        vresolvidaaut.tipo_registro         := substr(r_txt.texto, 8, 1);
        vresolvidaaut.cd_ecad_duplicidade   := substr(r_txt.texto, 9, 9);
        vresolvidaaut.cd_ecad_obra_musical  := substr(r_txt.texto, 18, 13);
        vresolvidaaut.dt_referencia         := recortadate(r_txt.texto, 31, 8);
        vresolvidaaut.cd_agrupador          := substr(r_txt.texto, 39, 15);
        
        INSERT INTO DUPLICIDADE_RESOLVIDA_AUT_TEMP VALUES vresolvidaaut;

      
      ELSIF vtipolinha = '7' THEN
        vagrupador                       := NULL;
        vagrupador.cd_sociedade_ecad     := substr(r_txt.texto, 1, 3);
        vagrupador.cD_agrupador          := substr(r_txt.texto, 9, 15);
        vagrupador.Dt_Encerramento       := recortadate(r_txt.texto, 24, 8);
      
        INSERT INTO DUPLICIDADE_AGRUPADOR_TEMP VALUES vagrupador;

      
      END IF;

    
      if (r_txt.linha mod 10000) = 0 then
        commit;
      end if;

    
    END LOOP;

  
    commit;
  
    --EXECUTE IMMEDIATE 'TRUNCATE TABLE DB_DUPLICIDADE.ARQUIVO_DUPLICIDADE';
  END;
  
  --Inicio do processo de carga na base principal
  
  PROCEDURE processarimportacao IS
    v_cd_duplicidade_existe NUMBER;
    --PR-61        v_cd_duplicidade_novo     NUMBER;
    v_cd_duplicidade NUMBER;
  
    vduplinha NUMBER;
  
  BEGIN
  
    FOR i IN (SELECT DT.CD_DUPLICIDADE,DT.DT_REFERENCIA,DT.MOTIVO_BLOQUEIO,DT.CD_AGRUPADOR              
                FROM DUPLICIDADE_TEMP DT
               ) LOOP
         
      -- Verifica se ja este AGRUPADOR cadastradO com o codigo igual
      SELECT COUNT(*)
        INTO v_cd_duplicidade_existe
        FROM DUPLICIDADE DUP
       WHERE DUP.CD_AGRUPADOR = I.CD_AGRUPADOR;
       --dbms_output.put_line('DUPLICIDADE EXISTE: '||(v_cd_duplicidade_existe));
       IF(v_cd_duplicidade_existe < 1)THEN
         
         SELECT SQ_CD_DUPLICIDADE.NEXTVAL
           INTO v_cd_duplicidade
           FROM dual;
      
        INSERT INTO DUPLICIDADE
          (CD_DUPLICIDADE_ABRAMUS,
           CD_DUPLICIDADE_ECAD,
           DT_REFERENCIA_ECAD,
           MOTIVO_BLOQUEIO,
           CD_AGRUPADOR
           )
        VALUES
          (
           v_cd_duplicidade,
           I.CD_DUPLICIDADE,
           I.DT_REFERENCIA,
           I.MOTIVO_BLOQUEIO,
           I.CD_AGRUPADOR);
       
       END IF;
    END LOOP;
 
  END;

end CARREGAR_DUPLICIDADES;
/
