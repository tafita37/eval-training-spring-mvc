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

-- Pack de formule et produit
    create or replace view v_pack_formule_produit
        as
            select
                produit.*,
                pack_formule.id_pack_formule,
                pack_formule.id_pack,
                pack_formule.quantite_produit
            from   
                produit 
            join
                pack_formule
                    on
                        pack_formule.id_produit=produit.id_produit;

-- PackFormule et cout de revient
    create or replace view v_pack_cout_revient
        as
            select
                pack.id_pack,
                coalesce(sum((cout_revient_unitaire/nb_unitaire)*quantite_produit), 0) as cout_revient_pack
            from
                v_pack_formule_produit
            right join
                pack 
                    on 
                        pack.id_pack=v_pack_formule_produit.id_pack
            group by
                pack.id_pack;

-- Paiement et vente de billet
    create or replace view v_paiement_vente_billet 
        as
            select
                vente_billet.*,
                coalesce(paiement.montant_payer, 0) as montant_payer
            from
                vente_billet
            left join
                paiement
                    on
                        paiement.id_vente_billet=vente_billet.id_vente_billet;

-- Etat de paiement par vente de billet
    create or replace view v_etat_paiement
        as  
            select
                ROW_NUMBER() OVER() AS id,
                id_vente_billet,
                sum(montant_payer) as montant_payer
            from 
                v_paiement_vente_billet
            group by
                id_vente_billet;

-- Etat de paiement par vente de billet par etudiant
    create or replace view v_etat_paiement_b_etudiant_b_vente_billet
        as
            select 
                id_vente_billet,
                prix_vente,
                id_etudiant,
                sum(montant_payer) as montant_payer,
                prix_vente-sum(montant_payer) as reste_payer
            from
                v_paiement_vente_billet
            group by
                id_vente_billet,
                prix_vente,
                id_etudiant;

-- Etat de paiement par etudiant
    create or replace view v_etat_paiement_b_etudiant
        as
            select 
                ROW_NUMBER() OVER() AS id,
                id_etudiant,
                sum(montant_payer) as montant_payer,
                sum(reste_payer) as reste_payer
            from
                v_etat_paiement_b_etudiant_b_vente_billet
            group by
                id_etudiant;

-- Etat de livraison
    create or replace view v_etat_livraison
        as
            select
                ROW_NUMBER() OVER() AS id,
                id_client,
                id_axe,
                id_etudiant,
                date_vente_billet,
                sum(quantite_vente_billet) as nb_pack,
                sum(prix_vente)*sum(quantite_vente_billet) as montant_total
            from 
                vente_billet
            group by
                id_client,
                id_axe,
                id_etudiant,
                date_vente_billet;
            
-- Nombre et montant de vente par pack
    create or replace view v_nombre_montant_vente_p_pack
        as
            select 
                ROW_NUMBER() OVER() AS id, 
                pack.id_pack, 
                coalesce(sum(quantite_vente_billet), 0) as quantite_vente_billet, 
                coalesce(sum(prix_vente*quantite_vente_billet), 0) as prix_vente 
            from 
                vente_billet 
            right join
                pack 
                    on
                        pack.id_pack=vente_billet.id_pack
            group by 
                pack.id_pack;