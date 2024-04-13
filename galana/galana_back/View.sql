-- Reste de produit en stock
    create or replace view v_reste_produit_stock_j
        as 
            select
                ROW_NUMBER() OVER() AS id,
                id_produit,
                id_station,
                sum(entree_produit-sortie_produit ) as reste_stock,
                date_mouvement_produit
            from 
                mouvement_produit
            group by
                id_produit,
                id_station,
                date_mouvement_produit;

-- Nombre de page de produit
    create or replace view v_nb_pages_produit
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                produit
                        )
                    ) 
            as nb_page_produit;

-- Liste des entrees de stock
    create or replace view v_entree_stock_produit
        as
            select
                *
            from    
                mouvement_produit
            where
                entree_produit!=0
                    and
                sortie_produit=0;

-- Nombre de page de produit
    create or replace view v_nb_pages_entree_stock_produit
        as
            select 
                ceil(count(*)::decimal/5) as nb_page_entree_stock_produit,
                id_station
            from 
                v_entree_stock_produit
            group by
                id_station;