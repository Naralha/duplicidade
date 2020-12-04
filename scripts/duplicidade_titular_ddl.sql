CREATE TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" 
   ( 
	"CD_DUPLICIDADE" NUMBER NOT NULL ENABLE, 
	"CD_ECAD_OBRA" NUMBER NOT NULL ENABLE, 
	"CD_SOCIETARIO_OBRA" NUMBER, 
	"CD_ECAD_TITULAR" NUMBER NOT NULL ENABLE, 
	"CD_SOCIETARIO_TITULAR" NUMBER, 
	"NM_TITULAR" VARCHAR2(100) NOT NULL ENABLE, 
	"PSEUDO_TITULAR" VARCHAR2(100), 
	"CD_CISAC_SOCIEDADE" NUMBER, 
	"CD_CATEGORIA" VARCHAR2(100) NOT NULL ENABLE, 
	"CD_SUB_CATEGORIA" VARCHAR2(100) NOT NULL ENABLE, 
	"DT_INICIO_CONTRATO" DATE, 
	"DT_FIM_CONTRATO" DATE, 
	"CD_LINK_ECAD" NUMBER NOT NULL ENABLE, 
	"PERCENT_PARTIC_TITULAR" NUMBER NOT NULL ENABLE, 
	"CD_AGRUPADOR" NUMBER NOT NULL ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TD_ABRAPRD" ;

  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_AGRUPADOR" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("PERCENT_PARTIC_TITULAR" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_LINK_ECAD" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_SUB_CATEGORIA" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_CATEGORIA" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("NM_TITULAR" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_ECAD_TITULAR" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_ECAD_OBRA" NOT NULL ENABLE);
  ALTER TABLE "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR" MODIFY ("CD_DUPLICIDADE" NOT NULL ENABLE);

   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_DUPLICIDADE" IS 'CODIGO ECAD DA DUPLICIDADE';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_ECAD_OBRA" IS 'CODIGO ECAD DA OBRA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_SOCIETARIO_OBRA" IS 'CODIGO SOCIETARIO DA OBRA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_ECAD_TITULAR" IS 'CODIGO ECAD DO TITULAR';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_SOCIETARIO_TITULAR" IS 'CODIGO SOCIETÁRIO DO TITULAR';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."NM_TITULAR" IS 'NOME DO TITULAR DA OBRA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."PSEUDO_TITULAR" IS 'PSEUDONIMO DO TITULAR DA OBRA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_CISAC_SOCIEDADE" IS 'CODIGO CISAC DA SOCIEDADE';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_CATEGORIA" IS 'CODIGO DA CATEGORIA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_SUB_CATEGORIA" IS 'CODIGO DA SUB-CATEGORIA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."DT_INICIO_CONTRATO" IS 'DATA DE INICIO DO CONTRATO DE    EDICAO';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."DT_FIM_CONTRATO" IS 'DATA DE FIM DO CONTRATO DE EDICAO';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_LINK_ECAD" IS 'CODIGO DO LINK ECAD';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."PERCENT_PARTIC_TITULAR" IS 'PERCENTUAL DE PARTICIPCA?O DO TITULAR NA OBRA';
   COMMENT ON COLUMN "DB_DUPLICIDADE"."DUPLICIDADE_TITULAR"."CD_AGRUPADOR" IS 'CODIGO DO AGRUPADOR DE DUPLICIDADE';
