-- Nombre et type de billet vendu par étudiant
    create or replace view v_stat_vente_etudiant
        as
            select 
                ROW_NUMBER() OVER() AS id,
                sum(quantite_vente_billet) as quantite_vendu, 
                id_pack, 
                id_etudiant 
            from 
                vente_billet 
            group by 
                id_pack, 
                id_etudiant;