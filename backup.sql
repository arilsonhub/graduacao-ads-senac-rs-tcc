PGDMP     7    (            
    s           roboks    9.4.5    9.4.5 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    25124    roboks    DATABASE     �   CREATE DATABASE roboks WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE roboks;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    5            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    5            �            3079    11855    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    209            �            1259    25127    configuracao    TABLE     �   CREATE TABLE configuracao (
    con_cod bigint NOT NULL,
    con_descricao character varying(255),
    con_valido integer,
    con_valor character varying(255)
);
     DROP TABLE public.configuracao;
       public         postgres    false    5            �            1259    25125    configuracao_con_cod_seq    SEQUENCE     z   CREATE SEQUENCE configuracao_con_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.configuracao_con_cod_seq;
       public       postgres    false    173    5            �           0    0    configuracao_con_cod_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE configuracao_con_cod_seq OWNED BY configuracao.con_cod;
            public       postgres    false    172            �            1259    25138    controleloteerro    TABLE     �   CREATE TABLE controleloteerro (
    clt_cod bigint NOT NULL,
    clt_datahora timestamp without time zone,
    clt_descricao character varying(255),
    clt_processo character varying(255),
    clt_lot_cod bigint
);
 $   DROP TABLE public.controleloteerro;
       public         postgres    false    5            �            1259    25136    controleloteerro_clt_cod_seq    SEQUENCE     ~   CREATE SEQUENCE controleloteerro_clt_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.controleloteerro_clt_cod_seq;
       public       postgres    false    5    175            �           0    0    controleloteerro_clt_cod_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE controleloteerro_clt_cod_seq OWNED BY controleloteerro.clt_cod;
            public       postgres    false    174            �            1259    25157    devedor    TABLE     �   CREATE TABLE devedor (
    dev_id bigint NOT NULL,
    dev_cod bigint,
    dev_cpf_cnpj character varying(255),
    dev_datainclusao timestamp without time zone,
    dev_nome character varying(255),
    dev_processado integer
);
    DROP TABLE public.devedor;
       public         postgres    false    5            �            1259    25155    devedor_dev_id_seq    SEQUENCE     t   CREATE SEQUENCE devedor_dev_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.devedor_dev_id_seq;
       public       postgres    false    179    5            �           0    0    devedor_dev_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE devedor_dev_id_seq OWNED BY devedor.dev_id;
            public       postgres    false    178            �            1259    25149    devprocessadolotetemp    TABLE     �   CREATE TABLE devprocessadolotetemp (
    dlt_cod bigint NOT NULL,
    dlt_valido integer,
    dlt_dev_cod bigint,
    dlt_lot_cod bigint
);
 )   DROP TABLE public.devprocessadolotetemp;
       public         postgres    false    5            �            1259    25147 !   devprocessadolotetemp_dlt_cod_seq    SEQUENCE     �   CREATE SEQUENCE devprocessadolotetemp_dlt_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.devprocessadolotetemp_dlt_cod_seq;
       public       postgres    false    177    5            �           0    0 !   devprocessadolotetemp_dlt_cod_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE devprocessadolotetemp_dlt_cod_seq OWNED BY devprocessadolotetemp.dlt_cod;
            public       postgres    false    176            �            1259    25168    divida    TABLE     �   CREATE TABLE divida (
    div_id bigint NOT NULL,
    div_cod bigint,
    div_datainclusao timestamp without time zone,
    div_dev_cod bigint
);
    DROP TABLE public.divida;
       public         postgres    false    5            �            1259    25166    divida_div_id_seq    SEQUENCE     s   CREATE SEQUENCE divida_div_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.divida_div_id_seq;
       public       postgres    false    5    181            �           0    0    divida_div_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE divida_div_id_seq OWNED BY divida.div_id;
            public       postgres    false    180            �            1259    25176    email    TABLE     {   CREATE TABLE email (
    email_cod bigint NOT NULL,
    email_endereco character varying(255),
    email_valido integer
);
    DROP TABLE public.email;
       public         postgres    false    5            �            1259    25174    email_email_cod_seq    SEQUENCE     u   CREATE SEQUENCE email_email_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.email_email_cod_seq;
       public       postgres    false    5    183            �           0    0    email_email_cod_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE email_email_cod_seq OWNED BY email.email_cod;
            public       postgres    false    182            �            1259    25455    funcionalidade    TABLE     �   CREATE TABLE funcionalidade (
    fun_cod integer NOT NULL,
    fun_descricao character varying(255),
    fun_mod_cod bigint,
    fun_valido integer
);
 "   DROP TABLE public.funcionalidade;
       public         postgres    false    5            �            1259    25458    funcionalidade_fun_cod_seq    SEQUENCE     |   CREATE SEQUENCE funcionalidade_fun_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.funcionalidade_fun_cod_seq;
       public       postgres    false    5    199            �           0    0    funcionalidade_fun_cod_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE funcionalidade_fun_cod_seq OWNED BY funcionalidade.fun_cod;
            public       postgres    false    200            �            1259    25184    lote    TABLE     �  CREATE TABLE lote (
    lot_cod bigint NOT NULL,
    lot_campanhacodigo bigint,
    lot_datainclusao timestamp without time zone,
    lot_datapagamento timestamp without time zone,
    lot_datavencimento timestamp without time zone,
    lot_descontohonorarios character varying(255),
    lot_descontopercentual character varying(255),
    lot_valido integer,
    lot_lts_cod bigint,
    lot_usu_cod bigint
);
    DROP TABLE public.lote;
       public         postgres    false    5            �            1259    25182    lote_lot_cod_seq    SEQUENCE     r   CREATE SEQUENCE lote_lot_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.lote_lot_cod_seq;
       public       postgres    false    185    5            �           0    0    lote_lot_cod_seq    SEQUENCE OWNED BY     7   ALTER SEQUENCE lote_lot_cod_seq OWNED BY lote.lot_cod;
            public       postgres    false    184            �            1259    25236    lotedevedor    TABLE     ^   CREATE TABLE lotedevedor (
    ldv_lot_cod bigint NOT NULL,
    ldv_dev_id bigint NOT NULL
);
    DROP TABLE public.lotedevedor;
       public         postgres    false    5            �            1259    25195 
   lotestatus    TABLE     {   CREATE TABLE lotestatus (
    lts_cod bigint NOT NULL,
    lts_descricao character varying(255),
    lts_valido integer
);
    DROP TABLE public.lotestatus;
       public         postgres    false    5            �            1259    25193    lotestatus_lts_cod_seq    SEQUENCE     x   CREATE SEQUENCE lotestatus_lts_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.lotestatus_lts_cod_seq;
       public       postgres    false    5    187            �           0    0    lotestatus_lts_cod_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE lotestatus_lts_cod_seq OWNED BY lotestatus.lts_cod;
            public       postgres    false    186            �            1259    25412    menu    TABLE     �   CREATE TABLE menu (
    mem_cod integer NOT NULL,
    mem_action character varying(255),
    mem_descricao character varying(255),
    mem_img character varying(255),
    mem_mct_cod bigint,
    mem_valido integer,
    mem_mem_cod bigint
);
    DROP TABLE public.menu;
       public         postgres    false    5            �            1259    25418    menu_mem_cod_seq    SEQUENCE     r   CREATE SEQUENCE menu_mem_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.menu_mem_cod_seq;
       public       postgres    false    197    5            �           0    0    menu_mem_cod_seq    SEQUENCE OWNED BY     7   ALTER SEQUENCE menu_mem_cod_seq OWNED BY menu.mem_cod;
            public       postgres    false    198            �            1259    25203    menucategoria    TABLE     y   CREATE TABLE menucategoria (
    mct_cod bigint NOT NULL,
    mct_nome character varying(255),
    mct_valido integer
);
 !   DROP TABLE public.menucategoria;
       public         postgres    false    5            �            1259    25201    menucategoria_mct_cod_seq    SEQUENCE     {   CREATE SEQUENCE menucategoria_mct_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.menucategoria_mct_cod_seq;
       public       postgres    false    189    5            �           0    0    menucategoria_mct_cod_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE menucategoria_mct_cod_seq OWNED BY menucategoria.mct_cod;
            public       postgres    false    188            �            1259    25461    menufuncionalidade    TABLE     �   CREATE TABLE menufuncionalidade (
    mef_cod integer NOT NULL,
    mef_fun_cod bigint,
    mef_mem_cod bigint,
    mef_valido integer
);
 &   DROP TABLE public.menufuncionalidade;
       public         postgres    false    5            �            1259    25464    menufuncionalidade_mef_cod_seq    SEQUENCE     �   CREATE SEQUENCE menufuncionalidade_mef_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.menufuncionalidade_mef_cod_seq;
       public       postgres    false    201    5            �           0    0    menufuncionalidade_mef_cod_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE menufuncionalidade_mef_cod_seq OWNED BY menufuncionalidade.mef_cod;
            public       postgres    false    202            �            1259    25466    modulo    TABLE     x   CREATE TABLE modulo (
    mod_cod integer NOT NULL,
    mod_descricao character varying(255),
    mod_valido integer
);
    DROP TABLE public.modulo;
       public         postgres    false    5            �            1259    25469    modulo_mod_cod_seq    SEQUENCE     t   CREATE SEQUENCE modulo_mod_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.modulo_mod_cod_seq;
       public       postgres    false    5    203            �           0    0    modulo_mod_cod_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE modulo_mod_cod_seq OWNED BY modulo.mod_cod;
            public       postgres    false    204            �            1259    25211    parcela    TABLE     �   CREATE TABLE parcela (
    par_id bigint NOT NULL,
    par_cod bigint,
    par_datainclusao timestamp without time zone,
    par_nu integer,
    par_div_id bigint
);
    DROP TABLE public.parcela;
       public         postgres    false    5            �            1259    25209    parcela_par_id_seq    SEQUENCE     t   CREATE SEQUENCE parcela_par_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.parcela_par_id_seq;
       public       postgres    false    191    5            �           0    0    parcela_par_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE parcela_par_id_seq OWNED BY parcela.par_id;
            public       postgres    false    190            �            1259    25219    perfil    TABLE     �   CREATE TABLE perfil (
    pef_cod bigint NOT NULL,
    pef_admin integer,
    pef_nome character varying(255),
    pef_valido integer
);
    DROP TABLE public.perfil;
       public         postgres    false    5            �            1259    25217    perfil_pef_cod_seq    SEQUENCE     t   CREATE SEQUENCE perfil_pef_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.perfil_pef_cod_seq;
       public       postgres    false    193    5            �           0    0    perfil_pef_cod_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE perfil_pef_cod_seq OWNED BY perfil.pef_cod;
            public       postgres    false    192            �            1259    25511    permissaoperfil    TABLE     �   CREATE TABLE permissaoperfil (
    per_cod integer NOT NULL,
    per_pef_cod bigint,
    per_fun_cod bigint,
    per_valido integer
);
 #   DROP TABLE public.permissaoperfil;
       public         postgres    false    5            �            1259    25514    permissaoperfil_per_cod_seq    SEQUENCE     }   CREATE SEQUENCE permissaoperfil_per_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.permissaoperfil_per_cod_seq;
       public       postgres    false    5    205            �           0    0    permissaoperfil_per_cod_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE permissaoperfil_per_cod_seq OWNED BY permissaoperfil.per_cod;
            public       postgres    false    206            �            1259    25516    permissaousuario    TABLE     �   CREATE TABLE permissaousuario (
    peu_cod integer NOT NULL,
    peu_usu_cod bigint,
    peu_fun_cod bigint,
    peu_valido integer
);
 $   DROP TABLE public.permissaousuario;
       public         postgres    false    5            �            1259    25519    permissaousuario_peu_cod_seq    SEQUENCE     ~   CREATE SEQUENCE permissaousuario_peu_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.permissaousuario_peu_cod_seq;
       public       postgres    false    5    207            �           0    0    permissaousuario_peu_cod_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE permissaousuario_peu_cod_seq OWNED BY permissaousuario.peu_cod;
            public       postgres    false    208            �            1259    25227    usuario    TABLE     �   CREATE TABLE usuario (
    usu_cod bigint NOT NULL,
    usu_nome character varying(255),
    usu_senha character varying(255),
    usu_usuario character varying(255),
    usu_valido integer,
    usu_pef_cod bigint
);
    DROP TABLE public.usuario;
       public         postgres    false    5            �            1259    25225    usuario_usu_cod_seq    SEQUENCE     u   CREATE SEQUENCE usuario_usu_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.usuario_usu_cod_seq;
       public       postgres    false    5    195            �           0    0    usuario_usu_cod_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE usuario_usu_cod_seq OWNED BY usuario.usu_cod;
            public       postgres    false    194            �           2604    25130    con_cod    DEFAULT     n   ALTER TABLE ONLY configuracao ALTER COLUMN con_cod SET DEFAULT nextval('configuracao_con_cod_seq'::regclass);
 C   ALTER TABLE public.configuracao ALTER COLUMN con_cod DROP DEFAULT;
       public       postgres    false    173    172    173            �           2604    25141    clt_cod    DEFAULT     v   ALTER TABLE ONLY controleloteerro ALTER COLUMN clt_cod SET DEFAULT nextval('controleloteerro_clt_cod_seq'::regclass);
 G   ALTER TABLE public.controleloteerro ALTER COLUMN clt_cod DROP DEFAULT;
       public       postgres    false    174    175    175            �           2604    25160    dev_id    DEFAULT     b   ALTER TABLE ONLY devedor ALTER COLUMN dev_id SET DEFAULT nextval('devedor_dev_id_seq'::regclass);
 =   ALTER TABLE public.devedor ALTER COLUMN dev_id DROP DEFAULT;
       public       postgres    false    179    178    179            �           2604    25152    dlt_cod    DEFAULT     �   ALTER TABLE ONLY devprocessadolotetemp ALTER COLUMN dlt_cod SET DEFAULT nextval('devprocessadolotetemp_dlt_cod_seq'::regclass);
 L   ALTER TABLE public.devprocessadolotetemp ALTER COLUMN dlt_cod DROP DEFAULT;
       public       postgres    false    176    177    177            �           2604    25171    div_id    DEFAULT     `   ALTER TABLE ONLY divida ALTER COLUMN div_id SET DEFAULT nextval('divida_div_id_seq'::regclass);
 <   ALTER TABLE public.divida ALTER COLUMN div_id DROP DEFAULT;
       public       postgres    false    180    181    181            �           2604    25179 	   email_cod    DEFAULT     d   ALTER TABLE ONLY email ALTER COLUMN email_cod SET DEFAULT nextval('email_email_cod_seq'::regclass);
 >   ALTER TABLE public.email ALTER COLUMN email_cod DROP DEFAULT;
       public       postgres    false    182    183    183            �           2604    25460    fun_cod    DEFAULT     r   ALTER TABLE ONLY funcionalidade ALTER COLUMN fun_cod SET DEFAULT nextval('funcionalidade_fun_cod_seq'::regclass);
 E   ALTER TABLE public.funcionalidade ALTER COLUMN fun_cod DROP DEFAULT;
       public       postgres    false    200    199            �           2604    25187    lot_cod    DEFAULT     ^   ALTER TABLE ONLY lote ALTER COLUMN lot_cod SET DEFAULT nextval('lote_lot_cod_seq'::regclass);
 ;   ALTER TABLE public.lote ALTER COLUMN lot_cod DROP DEFAULT;
       public       postgres    false    184    185    185            �           2604    25198    lts_cod    DEFAULT     j   ALTER TABLE ONLY lotestatus ALTER COLUMN lts_cod SET DEFAULT nextval('lotestatus_lts_cod_seq'::regclass);
 A   ALTER TABLE public.lotestatus ALTER COLUMN lts_cod DROP DEFAULT;
       public       postgres    false    187    186    187            �           2604    25420    mem_cod    DEFAULT     ^   ALTER TABLE ONLY menu ALTER COLUMN mem_cod SET DEFAULT nextval('menu_mem_cod_seq'::regclass);
 ;   ALTER TABLE public.menu ALTER COLUMN mem_cod DROP DEFAULT;
       public       postgres    false    198    197            �           2604    25206    mct_cod    DEFAULT     p   ALTER TABLE ONLY menucategoria ALTER COLUMN mct_cod SET DEFAULT nextval('menucategoria_mct_cod_seq'::regclass);
 D   ALTER TABLE public.menucategoria ALTER COLUMN mct_cod DROP DEFAULT;
       public       postgres    false    189    188    189            �           2604    25471    mef_cod    DEFAULT     z   ALTER TABLE ONLY menufuncionalidade ALTER COLUMN mef_cod SET DEFAULT nextval('menufuncionalidade_mef_cod_seq'::regclass);
 I   ALTER TABLE public.menufuncionalidade ALTER COLUMN mef_cod DROP DEFAULT;
       public       postgres    false    202    201            �           2604    25472    mod_cod    DEFAULT     b   ALTER TABLE ONLY modulo ALTER COLUMN mod_cod SET DEFAULT nextval('modulo_mod_cod_seq'::regclass);
 =   ALTER TABLE public.modulo ALTER COLUMN mod_cod DROP DEFAULT;
       public       postgres    false    204    203            �           2604    25214    par_id    DEFAULT     b   ALTER TABLE ONLY parcela ALTER COLUMN par_id SET DEFAULT nextval('parcela_par_id_seq'::regclass);
 =   ALTER TABLE public.parcela ALTER COLUMN par_id DROP DEFAULT;
       public       postgres    false    190    191    191            �           2604    25222    pef_cod    DEFAULT     b   ALTER TABLE ONLY perfil ALTER COLUMN pef_cod SET DEFAULT nextval('perfil_pef_cod_seq'::regclass);
 =   ALTER TABLE public.perfil ALTER COLUMN pef_cod DROP DEFAULT;
       public       postgres    false    193    192    193            �           2604    25521    per_cod    DEFAULT     t   ALTER TABLE ONLY permissaoperfil ALTER COLUMN per_cod SET DEFAULT nextval('permissaoperfil_per_cod_seq'::regclass);
 F   ALTER TABLE public.permissaoperfil ALTER COLUMN per_cod DROP DEFAULT;
       public       postgres    false    206    205            �           2604    25522    peu_cod    DEFAULT     v   ALTER TABLE ONLY permissaousuario ALTER COLUMN peu_cod SET DEFAULT nextval('permissaousuario_peu_cod_seq'::regclass);
 G   ALTER TABLE public.permissaousuario ALTER COLUMN peu_cod DROP DEFAULT;
       public       postgres    false    208    207            �           2604    25230    usu_cod    DEFAULT     d   ALTER TABLE ONLY usuario ALTER COLUMN usu_cod SET DEFAULT nextval('usuario_usu_cod_seq'::regclass);
 >   ALTER TABLE public.usuario ALTER COLUMN usu_cod DROP DEFAULT;
       public       postgres    false    195    194    195            �          0    25127    configuracao 
   TABLE DATA                     public       postgres    false    173   ը       �           0    0    configuracao_con_cod_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('configuracao_con_cod_seq', 1, false);
            public       postgres    false    172            �          0    25138    controleloteerro 
   TABLE DATA                     public       postgres    false    175   �       �           0    0    controleloteerro_clt_cod_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('controleloteerro_clt_cod_seq', 1, false);
            public       postgres    false    174            �          0    25157    devedor 
   TABLE DATA                     public       postgres    false    179   �       �           0    0    devedor_dev_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('devedor_dev_id_seq', 1, false);
            public       postgres    false    178            �          0    25149    devprocessadolotetemp 
   TABLE DATA                     public       postgres    false    177   8�       �           0    0 !   devprocessadolotetemp_dlt_cod_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('devprocessadolotetemp_dlt_cod_seq', 1, false);
            public       postgres    false    176            �          0    25168    divida 
   TABLE DATA                     public       postgres    false    181   R�       �           0    0    divida_div_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('divida_div_id_seq', 1, false);
            public       postgres    false    180            �          0    25176    email 
   TABLE DATA                     public       postgres    false    183   l�       �           0    0    email_email_cod_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('email_email_cod_seq', 1, true);
            public       postgres    false    182            �          0    25455    funcionalidade 
   TABLE DATA                     public       postgres    false    199   �       �           0    0    funcionalidade_fun_cod_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('funcionalidade_fun_cod_seq', 1, true);
            public       postgres    false    200            �          0    25184    lote 
   TABLE DATA                     public       postgres    false    185   S�       �           0    0    lote_lot_cod_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('lote_lot_cod_seq', 1, false);
            public       postgres    false    184            �          0    25236    lotedevedor 
   TABLE DATA                     public       postgres    false    196   m�       �          0    25195 
   lotestatus 
   TABLE DATA                     public       postgres    false    187   ��       �           0    0    lotestatus_lts_cod_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('lotestatus_lts_cod_seq', 1, false);
            public       postgres    false    186            �          0    25412    menu 
   TABLE DATA                     public       postgres    false    197   .�       �           0    0    menu_mem_cod_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('menu_mem_cod_seq', 1, true);
            public       postgres    false    198            �          0    25203    menucategoria 
   TABLE DATA                     public       postgres    false    189   ��       �           0    0    menucategoria_mct_cod_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('menucategoria_mct_cod_seq', 1, true);
            public       postgres    false    188            �          0    25461    menufuncionalidade 
   TABLE DATA                     public       postgres    false    201   -�       �           0    0    menufuncionalidade_mef_cod_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('menufuncionalidade_mef_cod_seq', 1, true);
            public       postgres    false    202            �          0    25466    modulo 
   TABLE DATA                     public       postgres    false    203   ��       �           0    0    modulo_mod_cod_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('modulo_mod_cod_seq', 1, true);
            public       postgres    false    204            �          0    25211    parcela 
   TABLE DATA                     public       postgres    false    191   ��       �           0    0    parcela_par_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('parcela_par_id_seq', 1, false);
            public       postgres    false    190            �          0    25219    perfil 
   TABLE DATA                     public       postgres    false    193   �       �           0    0    perfil_pef_cod_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('perfil_pef_cod_seq', 1, false);
            public       postgres    false    192            �          0    25511    permissaoperfil 
   TABLE DATA                     public       postgres    false    205   ��       �           0    0    permissaoperfil_per_cod_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('permissaoperfil_per_cod_seq', 1, true);
            public       postgres    false    206            �          0    25516    permissaousuario 
   TABLE DATA                     public       postgres    false    207   �       �           0    0    permissaousuario_peu_cod_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('permissaousuario_peu_cod_seq', 1, false);
            public       postgres    false    208            �          0    25227    usuario 
   TABLE DATA                     public       postgres    false    195   ��       �           0    0    usuario_usu_cod_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('usuario_usu_cod_seq', 1, false);
            public       postgres    false    194            �           2606    25135    configuracao_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY configuracao
    ADD CONSTRAINT configuracao_pkey PRIMARY KEY (con_cod);
 H   ALTER TABLE ONLY public.configuracao DROP CONSTRAINT configuracao_pkey;
       public         postgres    false    173    173            �           2606    25146    controleloteerro_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY controleloteerro
    ADD CONSTRAINT controleloteerro_pkey PRIMARY KEY (clt_cod);
 P   ALTER TABLE ONLY public.controleloteerro DROP CONSTRAINT controleloteerro_pkey;
       public         postgres    false    175    175            �           2606    25165    devedor_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY devedor
    ADD CONSTRAINT devedor_pkey PRIMARY KEY (dev_id);
 >   ALTER TABLE ONLY public.devedor DROP CONSTRAINT devedor_pkey;
       public         postgres    false    179    179            �           2606    25154    devprocessadolotetemp_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY devprocessadolotetemp
    ADD CONSTRAINT devprocessadolotetemp_pkey PRIMARY KEY (dlt_cod);
 Z   ALTER TABLE ONLY public.devprocessadolotetemp DROP CONSTRAINT devprocessadolotetemp_pkey;
       public         postgres    false    177    177            �           2606    25173    divida_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY divida
    ADD CONSTRAINT divida_pkey PRIMARY KEY (div_id);
 <   ALTER TABLE ONLY public.divida DROP CONSTRAINT divida_pkey;
       public         postgres    false    181    181            �           2606    25181 
   email_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY email
    ADD CONSTRAINT email_pkey PRIMARY KEY (email_cod);
 :   ALTER TABLE ONLY public.email DROP CONSTRAINT email_pkey;
       public         postgres    false    183    183            �           2606    25192 	   lote_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY lote
    ADD CONSTRAINT lote_pkey PRIMARY KEY (lot_cod);
 8   ALTER TABLE ONLY public.lote DROP CONSTRAINT lote_pkey;
       public         postgres    false    185    185            �           2606    25200    lotestatus_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY lotestatus
    ADD CONSTRAINT lotestatus_pkey PRIMARY KEY (lts_cod);
 D   ALTER TABLE ONLY public.lotestatus DROP CONSTRAINT lotestatus_pkey;
       public         postgres    false    187    187            �           2606    25490    mem_cod 
   CONSTRAINT     H   ALTER TABLE ONLY menu
    ADD CONSTRAINT mem_cod PRIMARY KEY (mem_cod);
 6   ALTER TABLE ONLY public.menu DROP CONSTRAINT mem_cod;
       public         postgres    false    197    197            �           2606    25208    menucategoria_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY menucategoria
    ADD CONSTRAINT menucategoria_pkey PRIMARY KEY (mct_cod);
 J   ALTER TABLE ONLY public.menucategoria DROP CONSTRAINT menucategoria_pkey;
       public         postgres    false    189    189            �           2606    25216    parcela_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY parcela
    ADD CONSTRAINT parcela_pkey PRIMARY KEY (par_id);
 >   ALTER TABLE ONLY public.parcela DROP CONSTRAINT parcela_pkey;
       public         postgres    false    191    191            �           2606    25224    perfil_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (pef_cod);
 <   ALTER TABLE ONLY public.perfil DROP CONSTRAINT perfil_pkey;
       public         postgres    false    193    193            �           2606    25474    pk_funcionalidade 
   CONSTRAINT     \   ALTER TABLE ONLY funcionalidade
    ADD CONSTRAINT pk_funcionalidade PRIMARY KEY (fun_cod);
 J   ALTER TABLE ONLY public.funcionalidade DROP CONSTRAINT pk_funcionalidade;
       public         postgres    false    199    199            �           2606    25476    pk_menufuncionalidade 
   CONSTRAINT     d   ALTER TABLE ONLY menufuncionalidade
    ADD CONSTRAINT pk_menufuncionalidade PRIMARY KEY (mef_cod);
 R   ALTER TABLE ONLY public.menufuncionalidade DROP CONSTRAINT pk_menufuncionalidade;
       public         postgres    false    201    201            �           2606    25478 	   pk_modulo 
   CONSTRAINT     L   ALTER TABLE ONLY modulo
    ADD CONSTRAINT pk_modulo PRIMARY KEY (mod_cod);
 :   ALTER TABLE ONLY public.modulo DROP CONSTRAINT pk_modulo;
       public         postgres    false    203    203            �           2606    25524    pk_permissaoperfil 
   CONSTRAINT     ^   ALTER TABLE ONLY permissaoperfil
    ADD CONSTRAINT pk_permissaoperfil PRIMARY KEY (per_cod);
 L   ALTER TABLE ONLY public.permissaoperfil DROP CONSTRAINT pk_permissaoperfil;
       public         postgres    false    205    205                        2606    25546    pk_permissaousuario 
   CONSTRAINT     `   ALTER TABLE ONLY permissaousuario
    ADD CONSTRAINT pk_permissaousuario PRIMARY KEY (peu_cod);
 N   ALTER TABLE ONLY public.permissaousuario DROP CONSTRAINT pk_permissaousuario;
       public         postgres    false    207    207            �           2606    25240    uk_7q0apvbbf47jgevvyogr90v4h 
   CONSTRAINT     [   ALTER TABLE ONLY devedor
    ADD CONSTRAINT uk_7q0apvbbf47jgevvyogr90v4h UNIQUE (dev_cod);
 N   ALTER TABLE ONLY public.devedor DROP CONSTRAINT uk_7q0apvbbf47jgevvyogr90v4h;
       public         postgres    false    179    179            �           2606    25235    usuario_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usu_cod);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    195    195                       2606    25271    fk_1iidqflo8akeuybyp9m1eilml    FK CONSTRAINT     }   ALTER TABLE ONLY parcela
    ADD CONSTRAINT fk_1iidqflo8akeuybyp9m1eilml FOREIGN KEY (par_div_id) REFERENCES divida(div_id);
 N   ALTER TABLE ONLY public.parcela DROP CONSTRAINT fk_1iidqflo8akeuybyp9m1eilml;
       public       postgres    false    191    181    2022                       2606    25246    fk_3iwpjdov6gxly3aqe1in7m5t1    FK CONSTRAINT     �   ALTER TABLE ONLY devprocessadolotetemp
    ADD CONSTRAINT fk_3iwpjdov6gxly3aqe1in7m5t1 FOREIGN KEY (dlt_dev_cod) REFERENCES devedor(dev_cod);
 \   ALTER TABLE ONLY public.devprocessadolotetemp DROP CONSTRAINT fk_3iwpjdov6gxly3aqe1in7m5t1;
       public       postgres    false    177    179    2020                       2606    25256    fk_fs8n56q0j2awpec31u83peuw3    FK CONSTRAINT     ~   ALTER TABLE ONLY divida
    ADD CONSTRAINT fk_fs8n56q0j2awpec31u83peuw3 FOREIGN KEY (div_dev_cod) REFERENCES devedor(dev_id);
 M   ALTER TABLE ONLY public.divida DROP CONSTRAINT fk_fs8n56q0j2awpec31u83peuw3;
       public       postgres    false    179    2018    181                       2606    25479    fk_funcionalidade_fun_mod_cod    FK CONSTRAINT     �   ALTER TABLE ONLY funcionalidade
    ADD CONSTRAINT fk_funcionalidade_fun_mod_cod FOREIGN KEY (fun_mod_cod) REFERENCES modulo(mod_cod);
 V   ALTER TABLE ONLY public.funcionalidade DROP CONSTRAINT fk_funcionalidade_fun_mod_cod;
       public       postgres    false    199    2044    203                       2606    25266    fk_g89uhd6idygi1t3gbl2ftl2kp    FK CONSTRAINT     }   ALTER TABLE ONLY lote
    ADD CONSTRAINT fk_g89uhd6idygi1t3gbl2ftl2kp FOREIGN KEY (lot_usu_cod) REFERENCES usuario(usu_cod);
 K   ALTER TABLE ONLY public.lote DROP CONSTRAINT fk_g89uhd6idygi1t3gbl2ftl2kp;
       public       postgres    false    2036    185    195            
           2606    25286    fk_jgk1ceig1y12oduc2vux8wwjv    FK CONSTRAINT     �   ALTER TABLE ONLY lotedevedor
    ADD CONSTRAINT fk_jgk1ceig1y12oduc2vux8wwjv FOREIGN KEY (ldv_lot_cod) REFERENCES lote(lot_cod);
 R   ALTER TABLE ONLY public.lotedevedor DROP CONSTRAINT fk_jgk1ceig1y12oduc2vux8wwjv;
       public       postgres    false    2026    185    196                       2606    25261    fk_lqjsjy7fk9eqn0plhvfjsbvl0    FK CONSTRAINT     �   ALTER TABLE ONLY lote
    ADD CONSTRAINT fk_lqjsjy7fk9eqn0plhvfjsbvl0 FOREIGN KEY (lot_lts_cod) REFERENCES lotestatus(lts_cod);
 K   ALTER TABLE ONLY public.lote DROP CONSTRAINT fk_lqjsjy7fk9eqn0plhvfjsbvl0;
       public       postgres    false    185    187    2028                       2606    25484 !   fk_menufuncionalidade_mef_fun_cod    FK CONSTRAINT     �   ALTER TABLE ONLY menufuncionalidade
    ADD CONSTRAINT fk_menufuncionalidade_mef_fun_cod FOREIGN KEY (mef_fun_cod) REFERENCES funcionalidade(fun_cod);
 ^   ALTER TABLE ONLY public.menufuncionalidade DROP CONSTRAINT fk_menufuncionalidade_mef_fun_cod;
       public       postgres    false    199    201    2040                       2606    25496 !   fk_menufuncionalidade_mef_mem_cod    FK CONSTRAINT     �   ALTER TABLE ONLY menufuncionalidade
    ADD CONSTRAINT fk_menufuncionalidade_mef_mem_cod FOREIGN KEY (mef_mem_cod) REFERENCES menu(mem_cod);
 ^   ALTER TABLE ONLY public.menufuncionalidade DROP CONSTRAINT fk_menufuncionalidade_mef_mem_cod;
       public       postgres    false    197    201    2038                       2606    25276    fk_mn7o38p6arow28l3yk97tj5e4    FK CONSTRAINT        ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_mn7o38p6arow28l3yk97tj5e4 FOREIGN KEY (usu_pef_cod) REFERENCES perfil(pef_cod);
 N   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_mn7o38p6arow28l3yk97tj5e4;
       public       postgres    false    2034    193    195                       2606    25241    fk_ogewaa80ed0s6ff476u8h71d2    FK CONSTRAINT     �   ALTER TABLE ONLY controleloteerro
    ADD CONSTRAINT fk_ogewaa80ed0s6ff476u8h71d2 FOREIGN KEY (clt_lot_cod) REFERENCES lote(lot_cod);
 W   ALTER TABLE ONLY public.controleloteerro DROP CONSTRAINT fk_ogewaa80ed0s6ff476u8h71d2;
       public       postgres    false    185    175    2026                       2606    25525    fk_permissaoperfil_pef_cod    FK CONSTRAINT     �   ALTER TABLE ONLY permissaoperfil
    ADD CONSTRAINT fk_permissaoperfil_pef_cod FOREIGN KEY (per_pef_cod) REFERENCES perfil(pef_cod);
 T   ALTER TABLE ONLY public.permissaoperfil DROP CONSTRAINT fk_permissaoperfil_pef_cod;
       public       postgres    false    2034    205    193                       2606    25530    fk_permissaoperfil_per_fun_cod    FK CONSTRAINT     �   ALTER TABLE ONLY permissaoperfil
    ADD CONSTRAINT fk_permissaoperfil_per_fun_cod FOREIGN KEY (per_fun_cod) REFERENCES funcionalidade(fun_cod);
 X   ALTER TABLE ONLY public.permissaoperfil DROP CONSTRAINT fk_permissaoperfil_per_fun_cod;
       public       postgres    false    199    205    2040                       2606    25535    fk_permissaousuario_peu_fun_cod    FK CONSTRAINT     �   ALTER TABLE ONLY permissaousuario
    ADD CONSTRAINT fk_permissaousuario_peu_fun_cod FOREIGN KEY (peu_fun_cod) REFERENCES funcionalidade(fun_cod);
 Z   ALTER TABLE ONLY public.permissaousuario DROP CONSTRAINT fk_permissaousuario_peu_fun_cod;
       public       postgres    false    199    2040    207                       2606    25540    fk_permissaousuario_peu_usu_cod    FK CONSTRAINT     �   ALTER TABLE ONLY permissaousuario
    ADD CONSTRAINT fk_permissaousuario_peu_usu_cod FOREIGN KEY (peu_usu_cod) REFERENCES usuario(usu_cod);
 Z   ALTER TABLE ONLY public.permissaousuario DROP CONSTRAINT fk_permissaousuario_peu_usu_cod;
       public       postgres    false    2036    195    207            	           2606    25281    fk_s7weyqkxtw8yllkif5ki3e87g    FK CONSTRAINT     �   ALTER TABLE ONLY lotedevedor
    ADD CONSTRAINT fk_s7weyqkxtw8yllkif5ki3e87g FOREIGN KEY (ldv_dev_id) REFERENCES devedor(dev_id);
 R   ALTER TABLE ONLY public.lotedevedor DROP CONSTRAINT fk_s7weyqkxtw8yllkif5ki3e87g;
       public       postgres    false    196    2018    179                       2606    25251    fk_sso2trr2str9gn57w8ew4kv1p    FK CONSTRAINT     �   ALTER TABLE ONLY devprocessadolotetemp
    ADD CONSTRAINT fk_sso2trr2str9gn57w8ew4kv1p FOREIGN KEY (dlt_lot_cod) REFERENCES lote(lot_cod);
 \   ALTER TABLE ONLY public.devprocessadolotetemp DROP CONSTRAINT fk_sso2trr2str9gn57w8ew4kv1p;
       public       postgres    false    185    2026    177                       2606    25547    menu_mem_mct_cod_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_mem_mct_cod_fkey FOREIGN KEY (mem_mct_cod) REFERENCES menucategoria(mct_cod);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_mem_mct_cod_fkey;
       public       postgres    false    189    197    2030                       2606    25491    menu_mem_mem_cod_fkey    FK CONSTRAINT     s   ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_mem_mem_cod_fkey FOREIGN KEY (mem_mem_cod) REFERENCES menu(mem_cod);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_mem_mem_cod_fkey;
       public       postgres    false    2038    197    197            �     x�Œ�N�@��<�܀m�O0� @�͸;�m��]��:z�A�bn��H�A#�3����3�.F�%���(Ξ�j#����H��U�H�Ub|�.L��<�t��jr?Z@��Ia�-�&x�X��Aa�c�m���_��ˇA���$f��0�5=[�N6�:إ%j�k{��y�{��5x�;pMi������D3��9��`�eE��|��8!�?��*/Q��8�����$�� PV�6�h�wjG��A^����Pʗ��0\�g��r�OLj}]eaX!�Zo��K�      �   
   x���          �   
   x���          �   
   x���          �   
   x���          �   d   x���v
Q���WH�M��Q� S���):��Լ�Ԣ��|�,1'3%_S!��'�5XA�PGA=�(3�8?O�85/1Y/1��!�V/9?W]G�PӚ�� ��"�      �   c   x���v
Q���WH+�K���K��LILIU� ���St@�)���E�ɉ�nn~
B��%_S!��'�5XA�PGA�%�8�)?�(E]G�7Դ��� /c#      �   
   x���          �   
   x���          �   �   x��Ͽ
�0�O�mm!K���!B�T��U�$b ́�:���?�����}\��4���''��,�B��a��������>)x�5.��T�B�'P�4���Th�m�.@�2��p'D�"��lw9�����"�0<Af�D��b���4Kn��E���z�      �   �   x���v
Q���W�M�+U��M͍O�O�Q 1�K2�� �������|737��M.Ah(K��L�����������a������X����X��� s��uSS2K@�qP���B}|4���� |73      �   ]   x���v
Q���W�M�+MN,IM�/�LT��M.�O�O�Q 1��sS!��Ĝ̔|M�0G�P�`Cu_�N���̼�̂�uCMk... �Mc      �   R   x���v
Q���W�M�+M+�K���K��LILIU��MM�O�O�Q 1��Nnj.�SҐ�������a�� E��\\\ � 5      �   V   x���v
Q���W��O)��W� ����): ���������|�,1'3%_S!��'�5XA�PGA�%�8�)?�(E]G�PӚ�� ���      �   
   x���          �   c   x���v
Q���W(H-J��Q�(HM�O�O�Q 1Sr3� ̼��T�,1'3%_S!��'�5XA�PG�@GA�hBbJ~�BJ�BQ~��-�:
���\\\ ۬ �      �   P   x���v
Q���W(H-��,.N�2�2s4�t|r~�H&� 5�I+�Cp�s2S�5�}B]�4u�HӚ�� ��W      �   
   x���          �   �   x���v
Q���W(-.M,��W� 2��St@"�y���Vqj^F"�	U
�%�d�@��i ��
a�>���
�:
�E�9��y
��y��'攥+�$*�'�e��ML�@�D�: ��PӚ�� |V4     