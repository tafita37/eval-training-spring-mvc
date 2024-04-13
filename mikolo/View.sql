-- Etat de stock du magasin centrale
    create or replace view v_etat_stock_magasin 
        as
            select 
                laptop.*,
                sum(quantite_laptop_entrant)-sum(quantite_laptop_sortant) as reste_stock,
                date_mouvement
            from
                stock_laptop_magasin
            join 
                laptop
                    on 
                        laptop.id_laptop=stock_laptop_magasin.id_laptop
            group by
                laptop.id_laptop,
                date_mouvement;

-- Etat de stock du point de vente
    create or replace view v_etat_stock_point_vente 
        as
            select 
                laptop.*,
                coalesce(sum(quantite_laptop_entrant)-sum(quantite_laptop_sortant), 0) as reste_stock,
                date_mouvement
            from
                stock_point_vente
            right join 
                laptop
                    on 
                        laptop.id_laptop=stock_point_vente.id_laptop
            group by
                laptop.id_laptop,
                date_mouvement;

-- Reste en stock 
    create or replace view v_reste_stock_magasin   
        as  
            select 
                laptop.*,
                sum(quantite_laptop_entrant)-sum(quantite_laptop_sortant) as reste_stock
            from
                stock_laptop_magasin
            join 
                laptop
                    on 
                        laptop.id_laptop=stock_laptop_magasin.id_laptop
            group by
                laptop.id_laptop;

-- Reste en stock au point de vente
    create or replace view v_reste_stock_laptop_point_vente 
        as
            select 
                id_laptop,
                sum(quantite_laptop_entrant)-sum(quantite_laptop_sortant) as reste_stock,
                date_mouvement
            from
                stock_point_vente
            group by
                id_laptop,
                date_mouvement;

-- Transfert en cours
    create or replace view v_transfert_laptop_en_cours 
        as
            select
                *
            from   
                transfert_laptop
            where 
                etat_transfert=0;

-- User et id point de vente
    create or replace view v_user_id_point_vente
        as 
            select 
                users.*,
                point_vente_users.id_point_vente_users,
                point_vente_users.id_point_vente
            from 
                users   
            join
                point_vente_users
            on 
                point_vente_users.id_users=users.id_users;

-- Nombre de pages d'une marque
    create or replace view v_nb_pages_marque
        as
            select 
                ceil(
                    (
                        select 
                            count(*)::decimal/5 
                        from 
                            marque
                    )
                    ) 
            as nb_page_marque;

-- Nombre de pages d'un processeur
    create or replace view v_nb_pages_processeur
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                processeur
                        )
                    ) 
            as nb_page_processeur;

-- Nombre de pages d'un type de ram
    create or replace view v_nb_pages_type_ram
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                type_ram
                        )
                    ) 
            as nb_page_type_ram;

-- Nombre de pages d'une ram
    create or replace view v_nb_pages_ram
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                ram
                        )
                    ) 
            as nb_page_ram;

-- Nombre de pages d'une type disque
    create or replace view v_nb_pages_type_disque
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                type_disque
                        )
                    ) 
            as nb_page_type_disque;

-- Nombre de pages d'un disque
    create or replace view v_nb_pages_disque
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                disque
                        )
                    ) 
            as nb_page_disque;

-- Nombre de pages d'un laptop
    create or replace view v_nb_pages_laptop
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                laptop
                        )
                    ) 
            as nb_page_laptop;

-- Nombre de page d'une vente de laptop
    create or replace view v_nb_pages_vente_laptop
        as
            select 
                ceil(
                        (
                            select 
                                count(*)::decimal/5 
                            from 
                                vente_laptop
                        )
                    ) 
            as nb_page_vente_laptop;

-- Statistique de vente total par mois par année
    create or replace view v_stat_vente_total_mois 
        as
            select 
                coalesce(sum(vl.quantite_laptop), 0)::integer as quantite_laptop, 
                m.num_mois::integer as num_mois,
                a.annee::integer as annee
            from 
                (
                    select 
                        generate_series(1, 12) as num_mois
                ) m
            cross join 
                (
                    select 
                        distinct extract(year from date_vente_laptop) as annee 
                    from vente_laptop
                ) a
            left join 
                vente_laptop vl 
            on 
                extract(month from vl.date_vente_laptop) = m.num_mois 
                    and 
                extract(year from vl.date_vente_laptop) = a.annee
            group by 
                m.num_mois, 
                a.annee
            order by
                m.num_mois, 
                a.annee;

-- Vente de laptop 
    create or replace view v_vente_laptop_point_vente 
        as 
            select
                vente_laptop.*,
                point_vente.nom_point_vente
            from 
                vente_laptop 
            join 
                point_vente 
                    on 
                        point_vente.id_point_vente=vente_laptop.id_point_vente;

-- Statistique de vente total par mois par année par point de vente
    create or replace view v_stat_vente_total_mois_pdv
        as
            select 
                coalesce(sum(vl.quantite_laptop), 0)::integer as quantite_laptop, 
                m.num_mois::integer as num_mois,
                a.annee::integer as annee,
                vl.id_point_vente,
                vl.nom_point_vente
            from 
                (
                    select generate_series(1, 12) as num_mois
                ) m
            cross join 
                (
                    select 
                        distinct extract(year from date_vente_laptop) as annee 
                    from v_vente_laptop_point_vente
                ) a
            left join 
                v_vente_laptop_point_vente vl 
            on 
                extract(month from vl.date_vente_laptop) = m.num_mois 
                    and 
                extract(year from vl.date_vente_laptop) = a.annee
            group by 
                m.num_mois, 
                a.annee, 
                vl.nom_point_vente, 
                vl.id_point_vente
            order by
                m.num_mois, 
                a.annee;

-- Reception de laptop et transfert
    create or replace view v_transfert_laptop_reception
        as
            select 
                tl.*,
                rl.id_reception_laptop,
                rl.quantite_recu,
                rl.date_reception,
                rl.quantite_perdu
            from 
                transfert_laptop tl
            join 
                reception_laptop rl
                    on rl.id_transfert_laptop=tl.id_transfert_laptop;

-- Prix de perte par mois par année
    create or replace view v_prix_perte_ma
        as
            select 
                sum(coalesce(quantite_perdu*prix_achat, 0)) as prix_perte,
                m.num_mois::integer as num_mois_reception,
                a.annee_reception::integer as annee_reception
            from 
                (
                    select 
                        generate_series(1, 12) as num_mois
                ) m
            cross join
                (
                    select 
                        distinct extract(year from date_reception) as annee_reception
                    from
                        v_transfert_laptop_reception
                ) a
            left join
                v_transfert_laptop_reception vtl
                    on 
                        m.num_mois=extract(month from vtl.date_reception)
                            and
                        a.annee_reception=extract(year from vtl.date_reception)
            group by    
                m.num_mois,
                a.annee_reception
            order by     
                m.num_mois,
                a.annee_reception;

-- Benefice sans perte
    create or replace view v_benefice_sans_perte_ma
        as
            select
                coalesce(sum(vl.prix_vente-vl.prix_achat), 0) as benefice,
                m.num_mois::integer as num_mois_vente,
                a.annee_vente_laptop::integer as annee_vente_laptop
            from
                (
                    select 
                        generate_series(1, 12) as num_mois
                ) m
            cross join
                (
                    select 
                        distinct extract(year from date_vente_laptop) as annee_vente_laptop
                    from
                        vente_laptop
                ) a
            left join
                vente_laptop vl
                    on
                        extract(month from vl.date_vente_laptop)=m.num_mois
                            and 
                        extract(year from vl.date_vente_laptop)=a.annee_vente_laptop
            group by
                num_mois_vente,
                annee_vente_laptop
            order by
                num_mois_vente,
                annee_vente_laptop;

-- Benefice total
    create or replace view v_benefice_total
        as
            select
                vb.benefice-vp.prix_perte as benefice_total,
                num_mois_vente::INTEGER as num_mois,
                annee_vente_laptop::INTEGER as annee
            from    
                v_benefice_sans_perte_ma vb
            join 
                v_prix_perte_ma vp 
                    on
                        vb.num_mois_vente=vp.num_mois_reception
                            and
                        vb.annee_vente_laptop=vp.annee_reception;