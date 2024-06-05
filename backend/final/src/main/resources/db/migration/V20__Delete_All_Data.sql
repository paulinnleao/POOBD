-- Para popular as tabelas com os novos inserts, foi necessário apagar todos os dados.
DELETE FROM public.viagens;
DELETE FROM public.pessoas;
DELETE FROM public.motoristas_veiculos;
DELETE FROM public.passageiros;
DELETE FROM public.veiculos;
DELETE FROM public.motoristas;
DELETE FROM public.proprietarios;
-- Criamos então um novo script gerando 15 elementos para cada tabela, assim populando mais todas.