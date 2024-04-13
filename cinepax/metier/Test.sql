select
         ROW_NUMBER() OVER() as id,
         film.id_film,
         coalesce(v_nb_vue_b_film_j.nb_vue, 0) as nb_vue,
         coalesce((v_nb_vue_b_film_j.nb_vue/(select count(*) from v_vente_billet_place where date_vente_billet='2024-04-05'))*100, 0) as pourcentage,
         coalesce(v_nb_vue_b_film_j.date_vente_billet, '2024-04-05') as date_vente_billet
 from
         (
                 select                  *
                 from
                         v_nb_vue_b_film_j
                 where
                         date_vente_billet='2024-04-05'
         ) as v_nb_vue_b_film_j
 right join
         film
                 on
                         v_nb_vue_b_film_j.id_film=film.id_film
 group by
         film.id_film,
         v_nb_vue_b_film_j.date_vente_billet,
         v_nb_vue_b_film_j.nb_vue