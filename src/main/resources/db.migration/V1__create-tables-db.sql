CREATE TABLE public.eventos
(
    id integer NOT NULL,
    descricao character varying(50) NOT NULL,
    data date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.usuarios
(
    id integer NOT NULL,
    email character varying(75) NOT NULL,
    nome character varying(75) NOT NULL,
    senha character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.inscricoes
(
    id integer NOT NULL,
    id_user integer NOT NULL,
    id_event integer NOT NULL,
    status integer NOT NULL,
    PRIMARY KEY (id_user),
    CONSTRAINT id_user FOREIGN KEY (id_user)
        REFERENCES public.usuarios (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_event FOREIGN KEY (id_event)
        REFERENCES public.eventos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public.logs
(
    id integer NOT NULL,
    descricao character varying NOT NULL,
    data character varying NOT NULL,
    PRIMARY KEY (id)
);
