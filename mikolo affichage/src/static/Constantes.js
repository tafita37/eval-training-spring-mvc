// Base url
export const baseUrl = "http://localhost:8080";

// Menu déroulant
export const listeClickDeroulant=
[
    {
        id : "M1",
        titleDeroulant : "Ordinateurs",
        classIcon : "",
        // classIcon : "fas fa-laptop-code ",
        pages : 
        [
            {
                id : "M1P1",
                name : "Liste",
                path : "/laptop/listeLaptop/1"
            },
            {
                id : "M1P2",
                name : "Insertion",
                path : "/laptop/newLaptop"
            }
        ]
    },
    {
        id : "M2",
        titleDeroulant : "Marque",
        classIcon : "",
        pages : 
        [
            {
                id : "M2P1",
                name : "Liste",
                path : "/marque/listeMarque/1"
            },
            {
                id : "M2P2",
                name : "Insertion",
                path : "/marque/newMarque"
            }
        ]
    },
    {
        id : "M3",
        titleDeroulant : "Processeur",
        classIcon : "",
        pages : 
        [
            {
                id : "M3P1",
                name : "Liste",
                path : "/processeur/listeProcesseur/1"
            },
            {
                id : "M3P2",
                name : "Insertion",
                path : "/processeur/insertProcesseur"
            }
        ]
    },
    {
        id : "M4",
        titleDeroulant : "Ram",
        classIcon : "",
        pages : 
        [
            {
                id : "M4P1",
                name : "Liste de ram",
                path : "/ram/listeRam/1"
            },
            {
                id : "M4P2",
                name : "Insertion de ram",
                path : "/ram/insertRam"
            },
            {
                id : "M4P3",
                name : "Liste de type de ram",
                path : "/ram/typeRam/listeTypeRam/1"
            },
            {
                id : "M4P4",
                name : "Insertion de type de ram",
                path : "/ram/typeRam/newTypeRam"
            }
        ]
    },
    {
        id : "M5",
        titleDeroulant : "Disque dur",
        classIcon : "",
        pages : 
        [
            {
                id : "M5P1",
                name : "Liste de disque dur",
                path : "/disque/listeDisque/1"
            },
            {
                id : "M5P2",
                name : "Insertion de disque dur",
                path : "/disque/insertDisque"
            },
            {
                id : "M5P3",
                name : "Liste de type de disque dur",
                path : "/disque/typeDisque/listeTypeDisque/1"
            },
            {
                id : "M5P4",
                name : "Insertion de type de disque dur",
                path : "/disque/typeDisque/newTypeDisque"
            }
        ]
    },
    {
        id : "M6",
        titleDeroulant : "Point de vente",
        classIcon : "",
        pages : 
        [
            {
                id : "M6P1",
                name : "Nouvel utilisateur",
                path : "/pdv/user/newUser"
            },
            {
                id : "M6P2",
                name : "Nouveau point de vente",
                path : "/pdv/newPdv"
            },
            {
                id : "M6P3",
                name : "Affectation",
                path : "/pdv/affectUserToPdv"
            }
        ]
    },
    {
        id : "M7",
        titleDeroulant : "Stock du magasin",
        classIcon : "",
        pages : 
        [
            {
                id : "M7P1",
                name : "Ajout de stock",
                path : "/laptop/ajoutStockCentral"
            },
            {
                id : "M7P2",
                name : "Transfert de stock",
                path : "/pdv/laptop/transfertLaptop"
            },
            {
                id : "M7P3",
                name : "Reception de renvoie de laptop",
                path : "/pdv/laptop/receptionRenvoieLaptop"
            }
        ]
    },
    {
        id : "M8",
        titleDeroulant : "Vente de laptop",
        classIcon : "",
        pages : 
        [
            {
                id : "M8P1",
                name : "Total par mois",
                path : "/laptop/statVenteMois"
            },
            {
                id : "M8P2",
                name : "Par point de vente",
                path : "/laptop/statVenteMoisPdv"
            },
            {
                id : "M8P3",
                name : "Benefice par mois",
                path : "/laptop/statBeneficeMois"
            }
        ]
    }
];


export const listeClickDeroulantTwo=
[
    {
        id : "M1",
        titleDeroulant : "Ordinateurs",
        classIcon : "",
        // classIcon : "fas fa-laptop-code ",
        pages : 
        [
            {
                id : "M1P1",
                name : "Reception",
                path : "/pdv/laptop/receptionLaptop"
            },
            {
                id : "M1P2",
                name : "Renvoyer",
                path : "/pdv/laptop/renvoieLaptop"
            },
            {
                id : "M1P3",
                name : "Vendre un laptop",
                path : "/pdv/laptop/venteLaptop"
            },
            {
                id : "M1P4",
                name : "Liste des ventes",
                path : "/pdv/laptop/listeVenteLaptop/1"
            }
        ]
    }
];

// Input d'insertion pour l'objet marque
export const insertMarque =
[
    {
        labelName : "Nom de marque",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomMarque",
        reactiveReference : "nomMarque"
    }
]

// Input d'insertion pour l'objet processeur
export const insertProcesseur =
[
    {
        labelName : "Nom du processeur",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomProcesseur",
        reactiveReference : "nomProcesseur"
    },
    {
        labelName : "Frequence du processeur",
        inputType : "number",
        placeHolder : "Frequence",
        idHTML : "inputFrequence",
        reactiveReference : "frequence"
    },
    {
        labelName : "Nombre du coeur du processeur",
        inputType : "number",
        placeHolder : "Nombre de coeur",
        idHTML : "inputNbCoeur",
        reactiveReference : "nbCoeur"
    }
]

// Input d'insertion pour l'objet type ram
export const insertTypeRam =
[
    {
        labelName : "Nom du type de ram",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomTypeRam",
        reactiveReference : "nomTypeRam"
    }
]

// Input d'insertion pour l'objet type de disque
export const insertTypeDisque =
[
    {
        labelName : "Nom du type de disque",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomTypeDisque",
        reactiveReference : "nomTypeDisque"
    }
]

// Input d'insertion pour l'objet ram
export const insertRamInput =
[
    {
        labelName : "Capacite",
        inputType : "number",
        placeHolder : "Capacite",
        idHTML : "inputCapacite",
        reactiveReference : "capacite"
    },
    {
        labelName : "Frequence",
        inputType : "number",
        placeHolder : "Frequence",
        idHTML : "inputFrequence",
        reactiveReference : "frequence"
    }
]

// Input d'insertion pour la vente de laptop
export const insertVenteLaptop =
[
    {
        labelName : "Date de vente",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDateVente",
        reactiveReference : "dateVenteLaptop"
    },
    {
        labelName : "Quantite de laptop",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantiteLaptop",
        reactiveReference : "quantiteLaptop"
    }
]

// Input d'insertion pour l'objet disque
export const insertDisqueInput =
[
    {
        labelName : "Capacite",
        inputType : "number",
        placeHolder : "Capacite",
        idHTML : "inputCapacite",
        reactiveReference : "capacite"
    },
    {
        labelName : "Vitesse",
        inputType : "number",
        placeHolder : "Vitesse",
        idHTML : "inputVitesse",
        reactiveReference : "vitesse"
    }
]

// Input d'insertion pour l'objet laptop
export const insertLaptopInput =
[
    {
        labelName : "Model",
        inputType : "text",
        placeHolder : "Model",
        idHTML : "inputModel",
        reactiveReference : "model"
    },
    {
        labelName : "Taille de l'ecran",
        inputType : "number",
        placeHolder : "Taille",
        idHTML : "inputTailleEcran",
        reactiveReference : "tailleEcran"
    },
    {
        labelName : "Prix d'achat",
        inputType : "number",
        placeHolder : "Prix d'achat",
        idHTML : "inputPrixAchat",
        reactiveReference : "prixAchat"
    },
    {
        labelName : "Prix de vente",
        inputType : "number",
        placeHolder : "Prix de vente",
        idHTML : "inputPrixVente",
        reactiveReference : "prixVente"
    }
]

// Input d'insertion pour l'objet users
export const insertUsers =
[
    {
        labelName : "Nom de l'utilisateur",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNom",
        reactiveReference : "nomUser"
    },
    {
        labelName : "Prenom de l'utilisateur",
        inputType : "text",
        placeHolder : "Prenom",
        idHTML : "inputPrenom",
        reactiveReference : "prenomUser"
    },
    {
        labelName : "Email",
        inputType : "email",
        placeHolder : "Email",
        idHTML : "inputEmail",
        reactiveReference : "emailUser"
    },
    {
        labelName : "Date de naissance",
        inputType : "date",
        placeHolder : "Date de naissance",
        idHTML : "inputDtnUser",
        reactiveReference : "dtnUser"
    },
    {
        labelName : "Mot de passe",
        inputType : "password",
        placeHolder : "Mot de passe",
        idHTML : "inputMdpUser",
        reactiveReference : "mdpUser"
    }
]

// Input d'insertion pour l'objet point de vente
export const insertPdv =
[
    {
        labelName : "Nom du point de vente",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomPointVente",
        reactiveReference : "nomPointVente"
    }
]

// Input d'insertion pour l'ajout de stock au magasin central
export const insertStockCentral =
[
    {
        labelName : "Quantite",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantite",
        reactiveReference : "quantiteLaptopEntrant"
    },
    {
        labelName : "Date",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDate",
        reactiveReference : "dateMouvement"
    }
]

// Input d'insertion pour le transfert de laptop
export const transfertStockLaptop =
[
    {
        labelName : "Quantite",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantite",
        reactiveReference : "quantiteLaptop"
    },
    {
        labelName : "Date",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDate",
        reactiveReference : "dateTransfertLaptop"
    }
]

// Input d'insertion pour le renvoie de laptop
export const insertRenvoieLaptop =
[
    {
        labelName : "Quantite",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantite",
        reactiveReference : "quantiteLaptop"
    },
    {
        labelName : "Date",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDate",
        reactiveReference : "dateRenvoieLaptop"
    }
]

// Input d'insertion pour la reception de laptop
export const insertReceptionLaptop =
[
    {
        labelName : "Quantite recu",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantiteRecu",
        reactiveReference : "quantiteRecu"
    },
    {
        labelName : "Date de reception",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDateReception",
        reactiveReference : "dateReception"
    }
]

// Input d'insertion pour la reception de renvoie de laptop
export const insertReceptionRenvoieLaptop =
[
    {
        labelName : "Quantite recu",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantiteRecu",
        reactiveReference : "quantiteRecu"
    },
    {
        labelName : "Date de reception",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDateReception",
        reactiveReference : "dateReception"
    }
]

// Input d'insertion pour l'affectation d'utilisateurs
export const insertAffectation =
[
    {
        labelName : "Debut d'affectation",
        inputType : "date",
        placeHolder : "Date de debut",
        idHTML : "inputDateDebutAffectation",
        reactiveReference : "dateDebutAffectation"
    }
]