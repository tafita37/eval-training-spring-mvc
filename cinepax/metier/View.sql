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

-- Vente de billet et place
    create or replace view v_vente_billet_place
        as
            select  
                vente_billet.*,
                place_vente_billet.id_place_vente_billet,
                place_vente_billet.id_place,
                place_vente_billet.id_tarif,
                billet.id_film,
                billet.id_salle,
                billet.date_heure_billet
            from 
                vente_billet
            join
                place_vente_billet
                    on
                        place_vente_billet.id_vente_billet=vente_billet.id_vente_billet
            join
                billet
                    on
                        billet.id_billet=vente_billet.id_billet;

-- Statistique de vente de billet
    create or replace view v_stat_vente_billet
        as
            select
                ROW_NUMBER() OVER() AS id,
                count(v_vente_billet_place.*) as nb_vente,
                film.id_film::integer
            from 
                v_vente_billet_place
            right join
                film
                    on 
                        film.id_film=v_vente_billet_place.id_film
            group by
                film.id_film;

-- Statistique de vente de billet par film avec pourcentage
    create or replace view v_stat_vente_billet_b_film
        as
            select
                *,
                (nb_vente/(select sum(nb_vente) from v_stat_vente_billet)*100)::double precision as pourcentage_vente
            from 
                v_stat_vente_billet
            order by
                nb_vente 
            desc;

-- Place et rangee
    create or replace view v_place_rangee
        as
            select
                rangee.*,
                place.id_place,
                place.numero_place
            from
                rangee
            join
                place
                    on
                        place.id_rangee=rangee.id_rangee
            order by
                rang_rangee,
                numero_place;

-- Place et etat d'occupation
    create or replace view v_place_etat
        as
            select 
                place.*, 
                place_vente_billet.id_place is not null as occupee,
                vente_billet.id_billet
            from 
                place_vente_billet 
            join
                vente_billet
                    on
                        vente_billet.id_vente_billet=place_vente_billet.id_vente_billet
            right join 
                place 
                    on 
                place_vente_billet.id_place=place.id_place;

-- Nombre de place par rangee
    create or replace view v_count_nb_place_b_rangee as 
        select 
            ROW_NUMBER() OVER()::integer AS id,
            count(id_place) as nb_place, 
            id_rangee, 
            id_salle
        from 
            v_place_rangee 
        group by 
            rang_rangee,
            id_rangee,
            id_salle
        order by
            rang_rangee;

-- Tarif par ordre décroissant des debut horaire
    create or replace view v_tarif_asc_debut_horaire 
        as
            select
                *
            from 
                tarif
            order by
                debut_horaire
            desc;

-- Vente billet place et tarif
    create or replace view v_vente_billet_place_tarif
        as
            select
                tarif.*,
                v_vente_billet_place.id_vente_billet,
                v_vente_billet_place.id_billet,
                v_vente_billet_place.date_vente_billet,
                v_vente_billet_place.id_place_vente_billet,
                v_vente_billet_place.id_place,
                v_vente_billet_place.id_film,
                v_vente_billet_place.id_salle,
                v_vente_billet_place.date_heure_billet
            from 
                v_vente_billet_place
            join
                tarif
                    on
                        tarif.id_tarif=v_vente_billet_place.id_tarif;

-- Chiffre d'affaire par jour
    create or replace view v_chiffre_affaire_b_film_j
        as
            select 
                id_film, 
                sum(prix_tarif) as prix_tarif,
                date_vente_billet
            from 
                v_vente_billet_place_tarif 
            group by 
                date_vente_billet,
                id_film;

-- Chiffre d'affaire total pour tout les films
    create or replace view v_chiffre_affaire_film_total
        as
            select 
                sum(prix_tarif) as prix_total,
                date_vente_billet
            from 
                v_vente_billet_place_tarif
            group by
                date_vente_billet;

-- Chiffre d'affaire de produit par jour
    create or replace view v_chiffre_affaire_b_produit_j
        as
            select
                id_produit,
                date_vente_produit,
                sum(quantite_vendu*prix_vente) as ca_produit
            from 
                vente_produit
            group by
                id_produit,
                date_vente_produit;

-- Chiffre d'affaire total par jour
    create or replace view v_chiffre_affaire_produit_total
        as
            select  
                date_vente_produit,
                sum(quantite_vendu*prix_vente) as ca_produit
            from
                vente_produit
            group by
                date_vente_produit;

-- Nombre de vues par film par jour
    create or replace view v_nb_vue_b_film_j
        as
            select
                id_film,
                count(*)::double precision as nb_vue,
                date_vente_billet
            from 
                v_vente_billet_place
            group by
                id_film,
                date_vente_billet;

-- Nombre de vue total par jour
    create or replace view v_nb_vue_total_j
        as
            select 
                count(*) as nb_vue, 
                date_vente_billet 
            from 
                v_vente_billet_place 
            group by 
                date_vente_billet;

-- Vente et billet
    create or replace view v_vente_billet as
        select
            vente_billet.id_vente_billet,
            vente_billet.date_vente_billet,
            billet.*
        from
            billet
        join
            vente_billet
                on
            billet.id_billet=vente_billet.id_billet;

-- Film salle et table temporaire
    create or replace view v_film_salle_tmp_table 
        as
            select
                s.*,
                f.*,
                tt.num_seance,
                tt.date_heure_seance
            from 
                tmp_table tt
                    join 
                        salle s
                            on 
                                tt.salle=s.nom_salle
                    join
                        film f
                            on
                                f.nom_film=tt.film;