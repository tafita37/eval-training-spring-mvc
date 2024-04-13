-- Database 
    create database galana;
    \c galana

-- Tables
    -- Station
        create table station(
            id_station serial primary key, 
            mail_station varchar(30) unique not null, 
            mdp_station text not null,
            role varchar(30) not null,
            unique(mail_station, mdp_station)
        );

    -- Admin
        create table admin(
            id_admin serial primary key, 
            mail_admin varchar(30) unique not null, 
            mdp_admin text not null,
            role varchar(30) not null,
            unique(mail_admin, mdp_admin)
        );

    -- Produit
        create table produit(
            id_produit serial primary key,
            nom_produit varchar(30) unique not null,
            prix_revient double precision not null,
            prix_vente double precision not null,
            pourcentage_evaporation_j double precision not null
        );

    -- Mouvement de produit
        create table mouvement_produit(
            id_mouvement_produit serial primary key, 
            id_produit int references produit(id_produit) not null, 
            id_station int references station(id_station) not null,
            entree_produit double precision not null, 
            sortie_produit double precision not null, 
            date_mouvement_produit date not null, 
            prix_revient double precision not null, 
            prix_vente double precision not null
        );