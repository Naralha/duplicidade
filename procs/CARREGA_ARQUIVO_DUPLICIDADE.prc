create or replace procedure CARREGA_ARQUIVO_DUPLICIDADE(A_PATH IN VARCHAR2,
                                                        A_NOME IN VARCHAR2) is

  vREGISTRO         ARQUIVO_DUPLICIDADE%ROWTYPE;
  vIOArquivoPointer UTL_FILE.FILE_TYPE;
  vIOLinhaArquivo   VARCHAR2(1000);
  vLinhaTxt         VARCHAR2(1000);
  vIOEof            BOOLEAN;
  vLinha            PLS_INTEGER;

  EFimArquvivo EXCEPTION;
  PRAGMA EXCEPTION_INIT(EFimArquvivo, 100);

begin

  execute immediate 'truncate table DB_DUPLICIDADE.ARQUIVO_DUPLICIDADE';

  vIOArquivoPointer := UTL_FILE.FOPEN(A_PATH, A_NOME, 'R');

  IF UTL_FILE.IS_OPEN(vIOArquivoPointer) THEN
  
    vIOEof := FALSE;
    vLinha := 0;
  
    LOOP
      <<LER_PROXIMA_LINHA>>
    
      BEGIN
        UTL_FILE.GET_LINE(vIOArquivoPointer, vIOLinhaArquivo);
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vIOEof := TRUE;
      END;
    
      EXIT WHEN vIOEof;
    
      IF NVL(LENGTH(vIOLinhaArquivo), 0) = 0 THEN
        GOTO LER_PROXIMA_LINHA;
      END IF;
    
      -----------------------------
      vLinha    := vLinha + 1;
      vLinhaTxt := translate(vIOLinhaArquivo,
                             '?-?O???`-_.E?OEY?O?#E?UU???A?IC??AIAU|?&/?',
                             '   O        ENOEY OA E UU  OAAIC  AIAU O   ');
    
      -- DBMS_OUTPUT.PUT_LINE(vLinhaTxt);
    
      vREGISTRO.Linha := vLinha;
      vREGISTRO.Texto := vLinhaTxt;
    
      INSERT INTO DB_DUPLICIDADE.ARQUIVO_DUPLICIDADE VALUES vREGISTRO;
    
      --   if vLinha > 350000 then
      --      DBMS_OUTPUT.PUT_LINE(to_char(vLinha));
      --   end if;   
    
      if (vLinha mod 10000) = 0 then
        commit;
      end if;
      ----------------------------
    
    END LOOP;
  
    UTL_FILE.FCLOSE(vIOArquivoPointer);
    COMMIT;
  
  END IF;

end CARREGA_ARQUIVO_DUPLICIDADE;
/
