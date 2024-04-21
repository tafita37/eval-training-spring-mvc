
-- equivalence(id_equivalence, id_unite1, id_unite2, quantite)


select 
    id_vente_billet,
    prix_vente,
    id_etudiant,
    sum(montant_payer) as montant_payer,
    prix_vente-sum(montant_payer) as reste_payer
from
    v_paiement_vente_billet
group by
    id_vente_billet,
    prix_vente,
    id_etudiant;