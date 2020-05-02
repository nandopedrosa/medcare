CREATE TABLE "usuario" (
  "id" SERIAL PRIMARY KEY,
  "email" varchar NOT NULL,
  "senha" varchar NOT NULL,
  "nome" varchar NOT NULL
);

CREATE TABLE "paciente" (
  "id" SERIAL PRIMARY KEY,
  "id_usuario" int NOT NULL,
  "nome" varchar NOT NULL,
  "sexo" varchar NOT NULL,
  "data_de_nascimento" int NOT NULL,
  "peso" decimal
);

CREATE TABLE "consulta" (
  "id" SERIAL PRIMARY KEY,
  "id_paciente" int NOT NULL,
  "data" bigint NOT NULL,
  "responsavel" varchar NOT NULL,
  "quadro" varchar NOT NULL,
  "conduta" varchar NOT NULL
);

CREATE TABLE "anexo_consulta" (
  "id" SERIAL PRIMARY KEY,
  "id_consulta" int NOT NULL,
  "nome" varchar NOT NULL,
  "arquivo" bytea NOT NULL,
  "filename" varchar NOT NULL
);

CREATE TABLE "medicamento" (
  "id" SERIAL PRIMARY KEY,
  "id_usuario" int NOT NULL,
  "nome" varchar NOT NULL,
  "indicacao" varchar NOT NULL,
  "posologia" varchar NOT NULL,
  "bula" bytea,
  "filename" varchar
);

CREATE TABLE "tratamento" (
  "id" SERIAL PRIMARY KEY,
  "id_paciente" int NOT NULL,
  "quadro" varchar NOT NULL
);

CREATE TABLE "tratamento_medicamento" (
  "id" SERIAL PRIMARY KEY,
  "id_tratamento" int NOT NULL,
  "id_medicamento" int NOT NULL
);

ALTER TABLE "paciente" ADD FOREIGN KEY ("id_usuario") REFERENCES "usuario" ("id");

ALTER TABLE "consulta" ADD FOREIGN KEY ("id_paciente") REFERENCES "paciente" ("id");

ALTER TABLE "anexo_consulta" ADD FOREIGN KEY ("id_consulta") REFERENCES "consulta" ("id");

ALTER TABLE "tratamento_medicamento" ADD FOREIGN KEY ("id_medicamento") REFERENCES "medicamento" ("id");

ALTER TABLE "tratamento_medicamento" ADD FOREIGN KEY ("id_tratamento") REFERENCES "tratamento" ("id");

ALTER TABLE "medicamento" ADD FOREIGN KEY ("id_usuario") REFERENCES "usuario" ("id");
