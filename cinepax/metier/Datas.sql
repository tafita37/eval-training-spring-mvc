-- Film
    insert into film(nom_film) values('Film1');
    insert into film(nom_film) values('Film2');
    insert into film(nom_film) values('Film3');
    insert into film(nom_film) values('Film4');
    insert into film(nom_film) values('Film5');

-- Salle
    insert into salle(nom_salle) values('Salle1');
    insert into salle(nom_salle) values('Salle2');
    insert into salle(nom_salle) values('Salle3');
    insert into salle(nom_salle) values('Salle4');
    insert into salle(nom_salle) values('Salle5');

-- Billet
    insert into billet(id_film, id_salle, date_heure_billet) values(1, 1, now()::timestamp);
    insert into billet(id_film, id_salle, date_heure_billet) values(2, 2, now()::timestamp);
    insert into billet(id_film, id_salle, date_heure_billet) values(3, 3, now()::timestamp);
    insert into billet(id_film, id_salle, date_heure_billet) values(4, 4, now()::timestamp);
    insert into billet(id_film, id_salle, date_heure_billet) values(5, 5, now()::timestamp);

-- Rangee
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('A', 1, 1);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('A', 2, 2);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('A', 3, 3);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('A', 4, 4);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('A', 5, 5);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('B', 2, 1);
    insert into rangee(nom_rangee, rang_rangee, id_salle) values('C', 3, 1);

-- Place
    insert into place(numero_place, id_rangee) values(1, 1);
    insert into place(numero_place, id_rangee) values(2, 2);
    insert into place(numero_place, id_rangee) values(3, 3);
    insert into place(numero_place, id_rangee) values(4, 4);
    insert into place(numero_place, id_rangee) values(5, 5);
    insert into place(numero_place, id_rangee) values(2, 6);
    insert into place(numero_place, id_rangee) values(1, 7);
    insert into place(numero_place, id_rangee) values(2, 1);

-- Produit
    insert into produit(nom_produit, prix_achat, prix_vente) values('Popcorn', 1000, 2000);
    insert into produit(nom_produit, prix_achat, prix_vente) values('Boissons', 10000, 20000);
    insert into produit(nom_produit, prix_achat, prix_vente) values('Lunette 3D', 100000, 20000);

-- Tarif
    insert into tarif(debut_horaire, prix_tarif, categorie_age) values('00:00'::time, 15000, 0);
    insert into tarif(debut_horaire, prix_tarif, categorie_age) values('12:00'::time, 30000, 0);
    insert into tarif(debut_horaire, prix_tarif, categorie_age) values('00:00'::time, 15000, 1);