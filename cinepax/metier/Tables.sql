-- Base
    create database cinepax;
    \c cinepax

-- Tables 
    -- Film
        create table film(
            id_film serial primary key, 
            nom_film varchar(30) unique not null
        );

    -- Salle
        create table salle(
            id_salle serial primary key, 
            nom_salle varchar(30) unique not null
        );

    -- Rangee
        create table rangee(
            id_rangee serial primary key, 
            nom_rangee varchar(30) not null, 
            rang_rangee int not null, 
            id_salle int references salle(id_salle) not null,
            unique(nom_rangee, id_salle),
            unique(rang_rangee, id_salle),
            unique(nom_rangee, rang_rangee, id_salle)
        );

    -- Place
        create table place(
            id_place serial primary key, 
            numero_place int not null, 
            id_rangee int references rangee(id_rangee) not null,
            unique(numero_place, id_rangee)
        );

        place_etat(id_place_etat, id_place, id_billet, etat)

    -- Billet
        create table billet(
            id_billet serial primary key, 
            id_film int references film(id_film) not null, 
            id_salle int references salle(id_salle) not null, 
            date_heure_billet timestamp not null,
            unique(id_film, id_salle, date_heure_billet)
        );

    -- Vente de billet
        create table vente_billet(
            id_vente_billet serial primary key, 
            id_billet int references billet(id_billet) not null, 
            date_vente_billet date not null
        );

    -- Tarif
        create table tarif(
            id_tarif serial primary key,
            debut_horaire time not null,
            prix_tarif double precision not null,
            categorie_age int not null,
            unique(debut_horaire, categorie_age)
        );

    -- Place vendu pour un billet
        create table place_vente_billet(
            id_place_vente_billet serial primary key, 
            id_place int references place(id_place) not null, 
            id_vente_billet int references vente_billet(id_vente_billet) not null,
            categorie_age int not null default 0,
            id_tarif int references tarif(id_tarif) not null default 1,
            unique(id_place, id_vente_billet)
        );

    -- Produit
        create table produit(
            id_produit serial primary key,
            nom_produit varchar(30) unique not null,
            prix_achat double precision not null,
            prix_vente double precision not null
        );

    -- Vente de produit
        create table vente_produit(
            id_vente_produit serial primary key,
            id_produit int references produit(id_produit) not null,
            date_vente_produit date not null,
            quantite_vendu int not null,
            prix_achat double precision not null,
            prix_vente double precision not null
        );