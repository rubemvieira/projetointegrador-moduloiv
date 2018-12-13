-- Table: public.tipo_bem

-- DROP TABLE public.tipo_bem;

CREATE TABLE public.tipo_bem
(
    id integer NOT NULL DEFAULT nextval('tipo_bem_id_seq'::regclass),
    descricao character varying(255) COLLATE pg_catalog."default",
    vida_util integer,
    depreciavel boolean,
    CONSTRAINT tipo_bem_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tipo_bem
    OWNER to postgres;

-- Table: public.bem

-- DROP TABLE public.bem;

CREATE TABLE public.bem
(
    id integer NOT NULL DEFAULT nextval('bem_id_seq'::regclass),
    descricao character varying(255) COLLATE pg_catalog."default",
    tipo integer NOT NULL,
    data_aquisicao date,
    valor_aquisicao numeric(15,2),
    vida_util integer,
    turno_trabalho numeric(15,2),
    valor_residual numeric(15,2),
    valor_residual_tipo character(1) COLLATE pg_catalog."default",
    data_venda date,
    valor_venda numeric(15,2),
    adquirido_usado boolean,
    tempo_uso integer,
    tempo_uso_tipo character(1) COLLATE pg_catalog."default",
    CONSTRAINT bem_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tipo_bem FOREIGN KEY (tipo)
        REFERENCES public.tipo_bem (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bem
    OWNER to postgres;    