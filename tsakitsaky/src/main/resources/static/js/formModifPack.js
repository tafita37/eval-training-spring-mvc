function ajouterPack(event) {
    event.preventDefault();
    var produit=document.getElementById("produit");

    // Création du premier élément (sélecteur)
    var selectElement = document.createElement('select');
    selectElement.name = 'idProduit[]';
    selectElement.className = 'form-control';

    // Création de l'option pour le sélecteur
    var optionElement = document.createElement('option');
    optionElement.value = '';
    optionElement.textContent = 'Choisissez un produit';

    // Ajout de l'option au sélecteur
    selectElement.appendChild(optionElement);
    $.ajax({
        url: '/ventePack/produit/allProduit',  // Spécifiez l'URL à laquelle vous souhaitez envoyer la requête
        type: 'GET',  // Type de requête (GET, POST, etc.)
        success: function (data) {
            data.map((liste, index) => {
                var option=document.createElement("option");
                option.value=liste.idProduit;
                option.textContent=liste.nomProduit;
                selectElement.appendChild(option);
            });
        },
        error: function (error) {
            // Fonction appelée en cas d'erreur de la requête
            console.error('Erreur de la requête :', error);
        }
    });

    // Création du deuxième élément (input de type nombre)
    var inputElement = document.createElement('input');
    inputElement.type = 'number';
    inputElement.name = 'quantiteProduit[]';
    inputElement.className = 'form-control ml-1';
    inputElement.placeholder = 'Quantite';

    var divFormGroup=document.createElement('div');
    divFormGroup.className='form-group';

    var divInputGroup=document.createElement('div');
    divInputGroup.className='input-group';
    
    divInputGroup.appendChild(selectElement);
    divInputGroup.appendChild(inputElement);

    divFormGroup.appendChild(divInputGroup);

    produit.appendChild(divFormGroup);
}
