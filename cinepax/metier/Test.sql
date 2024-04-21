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



"INSERT INTO billet (id_billet, id_film, id_salle, date_heure_billet) " +
               "SELECT s.NumSeance, f.id, sl.id, s.Date " +
               "FROM TaTableSeance s " +
               "JOIN Film f ON s.Film = f.Film " +
               "JOIN Salle sl ON s.Salle = sl.Salle"