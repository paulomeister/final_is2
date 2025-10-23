DROP TABLE IF EXISTS facturas_acueducto;

CREATE TABLE facturas_acueducto (
    id SERIAL PRIMARY KEY,
    id_cliente VARCHAR(10),
    periodo VARCHAR(6),
    consumo_m3 INTEGER,
    valor_pagar DECIMAL(10,2)
);

-- ===== INSERTS DE FACTURAS =====
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500004', '202503', 6, 39511.57);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500000', '202509', 23, 151293.51);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500008', '202509', 7, 47432.06);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500008', '202508', 12, 68189.82);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202502', 24, 152891.14);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202505', 8, 45611.87);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500000', '202510', 13, 77857.45);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202508', 24, 158062.46);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202508', 31, 179028.73);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202505', 5, 28389.14);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500002', '202505', 31, 171795.55);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500005', '202510', 31, 183560.59);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202504', 7, 39736.19);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202503', 34, 199767.58);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202509', 27, 177449.00);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202510', 25, 138925.79);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500005', '202506', 34, 206589.33);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500002', '202507', 15, 93393.07);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202506', 5, 28484.94);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202509', 20, 118900.55);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500008', '202509', 25, 142661.70);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500002', '202508', 31, 205575.83);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500004', '202507', 29, 175702.28);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500002', '202501', 33, 199843.55);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202509', 11, 74163.04);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500005', '202509', 27, 149298.75);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202503', 32, 189436.36);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500000', '202501', 26, 158396.39);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202504', 16, 93484.03);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202509', 34, 216674.34);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500002', '202507', 18, 120456.41);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202510', 30, 173844.74);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202506', 15, 92254.84);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202504', 24, 139704.93);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202508', 29, 187538.52);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202507', 25, 165928.09);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500008', '202505', 19, 116812.94);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500003', '202503', 33, 208322.17);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202510', 21, 137575.20);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500003', '202509', 25, 169281.80);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500003', '202501', 15, 94056.45);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500009', '202502', 27, 157857.43);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500000', '202510', 35, 204390.37);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202502', 26, 155660.21);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500008', '202503', 28, 183346.89);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500005', '202502', 29, 185146.66);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500001', '202510', 15, 98042.10);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202505', 29, 172878.22);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500007', '202505', 9, 50974.51);
INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES ('1234500006', '202503', 8, 44176.23);