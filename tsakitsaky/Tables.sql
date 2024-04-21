-- Database
    \c postgres
    drop database tsakitsaky;
    create database tsakitsaky;
    \c tsakitsaky

-- Tables
    -- Etudiant
        create table etudiant(
            id_etudiant serial primary key, 
            email_etudiant varchar(30) unique not null,
            prenom_etudiant varchar(30) not null, 
            mdp_etudiant varchar(30) not null
        );

    -- Admin
        create table admin_user(
            id_admin_user serial primary key, 
            email_admin_user varchar(30) unique not null,
            prenom_admin_user varchar(30) not null, 
            mdp_admin_user varchar(30) not null
        );

    -- Pack
        create table pack(
            id_pack serial primary key, 
            nom_pack varchar(30) unique not null, 
            photo_pack text, 
            cout_revient_pack double precision not null, 
            prix_vente_pack double precision not null,
            date_suppression date
        );

    -- Unite
        create table unite(
            id_unite serial primary key, 
            nom_unite varchar(30) unique not null
        );

    -- Produit
        create table produit(
            id_produit serial primary key, 
            nom_produit varchar(30) unique not null, 
            id_unite int references unite(id_unite), 
            cout_revient_unitaire double precision,
            nb_unitaire double precision
        );

    -- Formule de pack
        create table pack_formule(
            id_pack_formule serial primary key, 
            id_pack int references pack(id_pack) not null, 
            id_produit int references produit(id_produit) not null, 
            quantite_produit double precision not null,
            unique(id_pack, id_produit)
        );
    
    -- Axe
        create table axe(
            id_axe serial primary key,
            nom_axe varchar(30) unique not null
        );
    
    -- Quartier
        create table quartier(
            id_quartier serial primary key,
            nom_quartier varchar(30) unique not null,
            id_axe int references axe(id_axe) not null
        );

    -- Client
        create table client(
            id_client serial primary key,
            nom_client varchar(30) not null,
            contact_client varchar(30) unique not null,
            id_axe int references axe(id_axe)
        );

    -- Vente de billet
        create table vente_billet(
            id_vente_billet serial primary key, 
            id_pack int references pack(id_pack) not null, 
            quantite_vente_billet double precision not null, 
            date_vente_billet date not null, 
            cout_revient double precision not null, 
            prix_vente double precision not null, 
            id_etudiant int references etudiant(id_etudiant) not null, 
            id_client int references client(id_client) not null,
            id_axe int references axe(id_axe) not null,
            est_paye int not null
        );

    -- Paiement
        create table paiement(
            id_paiement serial primary key, 
            id_vente_billet int references vente_billet(id_vente_billet) not null, 
            montant_payer double precision not null, 
            date_paiement date not null
        );