// Base url
export const baseUrl = "http://localhost:8080";

// Nom du token de admin
export const tokenAdminName="tokenAdmin";

// Nom du token de station
export const tokenStationName="tokenStation";

// Menu déroulant
export const listeClickDeroulant=
[
    {
        id : "M1",
        titleDeroulant : "Produit",
        classIcon : "",
        // classIcon : "fas fa-laptop-code ",
        pages : 
        [
            {
                id : "M1P1",
                name : "Insertion",
                path : "/produit/newProduit"
            },
            {
                id : "M1P2",
                name : "Liste",
                path : "/produit/listeProduit/1"
            }
        ]
    }
];


export const listeClickDeroulantTwo=
[
    {
        id : "M1",
        titleDeroulant : "Stock",
        classIcon : "",
        pages : 
        [
            {
                id : "M1P1",
                name : "Ajout de stock",
                path : "/stock/ajoutStock"
            },
            {
                id : "M1P1",
                name : "Vendre un produit",
                path : "/stock/venteProduit"
            },
            {
                id : "M1P3",
                name : "Entrees de stock",
                path : "/stock/listeEntreeStock/1"
            }
        ]
    }
];

// Input d'insertion pour la creation de nouveau produit
export const insertProduit =
[
    {
        labelName : "Nom",
        inputType : "text",
        placeHolder : "Nom",
        idHTML : "inputNomProduit",
        reactiveReference : "nomProduit"
    },
    {
        labelName : "Prix de revient",
        inputType : "number",
        placeHolder : "Prix de revient",
        idHTML : "inputPrixRevient",
        reactiveReference : "prixRevient"
    },
    {
        labelName : "Prix de vente",
        inputType : "number",
        placeHolder : "Prix de vente",
        idHTML : "inputPrixVente",
        reactiveReference : "prixVente"
    },
    {
        labelName : "Pourcentage d'evaporation/jour",
        inputType : "number",
        placeHolder : "Pourcentage",
        idHTML : "inputPourcentageEvaporationJ",
        reactiveReference : "pourcentageEvaporationJ"
    }
]

// Input d'insertion pour l'ajout de stock au magasin central
export const insertStockStation =
[
    {
        labelName : "Quantite",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantite",
        reactiveReference : "entreeProduit"
    },
    {
        labelName : "Date d'ajout",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDate",
        reactiveReference : "dateMouvementProduit"
    }
]

// Input d'insertion pour l'ajout de stock au magasin central
export const insertventeProduit =
[
    {
        labelName : "Quantite",
        inputType : "number",
        placeHolder : "Quantite",
        idHTML : "inputQuantite",
        reactiveReference : "quantite"
    },
    {
        labelName : "Date de vente",
        inputType : "date",
        placeHolder : "Date",
        idHTML : "inputDate",
        reactiveReference : "date"
    }
]