-- Etudiants
    insert into etudiant(email_etudiant, prenom_etudiant, mdp_etudiant) values('taf.rand37@gmail.com', 'Tafita', '0000');

-- Admin
    insert into admin_user(email_admin_user, prenom_admin_user, mdp_admin_user) values('admin@gmail.com', 'ADMIN', '0000');

-- Unite
    insert into unite(nom_unite) values('g'), 
                                        ('piece');

-- Produit
    insert into produit(nom_produit, id_unite, cout_revient_unitaire, nb_unitaire) values('Tsatsiou', 1, 10000, 100),
                                                                                            ('Saucisse fumee', 1, 2000, 250),
                                                                                            ('Boulette chinoise', 2, 2000, 1),
                                                                                            ('Poulet fumee', 1, 3000, 250),
                                                                                            ('Cochon laquée', 1, 10000, 200),
                                                                                            ('Beignet de crevette', 2, 300, 1),
                                                                                            ('Emballage', 2, 1000, 1);

-- Pack
    insert into pack(
        nom_pack, 
        photo_pack, 
        cout_revient_pack, 
        prix_vente_pack
    ) values('20000ar', '20000ar.jpg', 10116, 20000),
            ('30000ar', '30000ar.png', 10460, 30000);

-- Formule de pack
    insert into pack_formule(id_pack, id_produit, quantite_produit) values(1, 1, 1),
                                                                            (1, 2, 2),
                                                                            (1, 3, 3),
                                                                            (1, 7, 4),
                                                                            (2, 4, 5),
                                                                            (2, 5, 6),
                                                                            (2, 6, 7),
                                                                            (2, 7, 8);