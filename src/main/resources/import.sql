-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- View: gaiaadm.v_recompensas_productos_periodo

-- DROP VIEW gaiaadm.v_recompensas_productos_periodo;
-- Creación de la vista e inserción de datos generales iniciales

CREATE OR REPLACE VIEW gaiaadm.v_recompensas_productos_periodo
 AS
 SELECT rec.rec_id,
    rec.rec_aportesoles,
    rec.rec_descripcion,
    rec.rec_producto,
    ppv.pvt_preciomaximo,
    rec.rec_nombre,
    rec.rec_puntosreq,
    rec.rec_stock,
    rec.rec_periodo,
    prod.prd_nombre,
    prod.prd_slug,
    undmedpres.umd_abreviatura AS umd_pres_abreviatura,
    undmed.umd_abreviatura,
    pres.ppd_nombre,
    prod.prd_sku,
    marc.mrc_nombre,
    prod.prd_cantidadmedida,
    string_agg(img.img_url::text, ','::text ORDER BY img.img_id) AS imagen_urls
   FROM gaiaadm.t_producto prod
     JOIN gaiaadm.t_imagenproducto img ON prod.prd_id = img.img_producto
     JOIN gaiaadm.t_precioventa ppv ON ppv.pvt_id = prod.prd_id
     JOIN gaiaadm.t_unidadmedida undmedpres ON prod.prd_undmedpresentacion = undmedpres.umd_id
     JOIN gaiaadm.t_unidadmedida undmed ON prod.prd_unidadmedida = undmed.umd_id
     JOIN gaiaadm.t_recompensas rec ON rec.rec_producto = prod.prd_id
     JOIN gaiaadm.t_presentacionproducto pres ON pres.ppd_id = prod.prd_presentacion
     JOIN gaiaadm.t_marca marc ON marc.mrc_id = prod.prd_marca
  GROUP BY rec.rec_id, prod.prd_sku, rec.rec_aportesoles, rec.rec_descripcion, rec.rec_producto, ppv.pvt_preciomaximo, rec.rec_nombre, rec.rec_puntosreq, rec.rec_stock, rec.rec_periodo, prod.prd_nombre, prod.prd_slug, undmedpres.umd_abreviatura, undmed.umd_abreviatura, pres.ppd_nombre, marc.mrc_nombre, prod.prd_cantidadmedida;

ALTER TABLE gaiaadm.v_recompensas_productos_periodo
    OWNER TO ecommerceuser;


INSERT INTO gaiaadm.t_general_info (
    gi_pbienvenida,
    gi_pporcompra,
    gi_valorcompra,
    gi_vigenciapuntos,
    gi_valorpuntos,
    gi_fechaActualizacion,
    gi_alerta_vencimiento
) VALUES (
    20,
    1,
    10.0,
    18,
    0.1,
    '2025-09-08',
    15
);