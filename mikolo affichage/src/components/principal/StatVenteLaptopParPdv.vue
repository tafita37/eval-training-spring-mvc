<script setup>
    import GeneralContain from '../reeuse/GeneralContain.vue';
    import HeaderOne from '../reeuse/HeaderOne.vue';
    import MenuDeroulantOne from '../reeuse/MenuDeroulantOne.vue';
    import SousGeneralContain from '../reeuse/SousGeneralContain.vue';
    import PrincipalContain from '../reeuse/PrincipalContain.vue';
    import SousPrincipal from '../reeuse/SousPrincipal.vue';
    import FooterOne from '../reeuse/FooterOne.vue';
    import H4CardTitle from '../reeuse/H4CardTitle.vue';
    import CardDescription from '../reeuse/CardDescription.vue';
    import TableHead from '../reeuse/TableHead.vue';
    import TableContain from '../reeuse/TableContain.vue';
    import axios from 'axios';
    import { onMounted, reactive, ref } from 'vue';
    import { baseUrl } from '@/static/Constantes';
    import { Bar } from 'vue-chartjs';
    import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
    import NormalSearch from '../reeuse/NormalSearch.vue';
    import html2pdf from 'html2pdf.js';

    const exportToPDF = () => {
        try {
            html2pdf(document.querySelector('.generalContain'), {
                margin : 1,
                filename : 'test.pdf'
            })
        } catch (error) {
            console.error(error);
        }
    };

    const allStat=reactive({});
    const allPointVente=reactive({});
    const chartOptions= {responsive: true};
    const recherche=reactive({
        pointVente : {
            idPointVente : 0
        }
    });
    
    ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

    const chartData = reactive({
        labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [ 
            {
                label : "Quantite vendu", 
                backgroundColor: '#f87979',
                data: [] 
            } 
        ]
    });

    const getAllPointVente = async () => {
        try {
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/pdv/allPointVente';
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allPointVente, response.data.data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const getAllStat = async () => {
        try {
            // chartData.datasets[0].data.splice(0, chartData.datasets[0].data.length);
            const token=localStorage.getItem("tokenMagasin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = baseUrl+'/laptop/statVenteMoisPDV';
            const params=new URLSearchParams();
            if(recherche.annee) {
                params.append("annee", recherche.annee);
            } else {
                params.append("annee", new Date().getFullYear());
            }
            params.append("idPointVente", recherche.pointVente.idPointVente);
            const response = await axios.get(urlApiSpringBoot, { params, headers });
            Object.assign(allStat, response.data);
            if(allStat.data.length==0) {    
                for(let i=0; i<12; i++) {
                    chartData.datasets[0].data[i]=0;
                }
            }
            for(let i=0; i<allStat.data.length; i++) {
                chartData.datasets[0].data[allStat.data[i].num_mois-1]=allStat.data[i].quantiteLaptop;
            }
            // for(let i=0; i<allStat.data.length; i++) {
            //     chartData.datasets[0].data[i]=allStat.data[i].quantiteLaptop;
            // }
            console.log(chartData.datasets[0].data);
        } catch (error) {
            console.log(error);
            if(error.response.status==403) {
                router.push("/");
            }
        }
    };

    const listeSelect = [
        {
            idHTML : "inputListePointVente",
            boucleObject : allPointVente,
            stringAfficher : "nomPointVente",
            stringId : "idPointVente",
            sousObject : "pointVente",
            defaultChoice : "Choisissez un point de vente"
        }
    ]

    onMounted(() => {
        getAllStat();
        getAllPointVente();
    });
</script>
<template>
    <GeneralContain class="">
        <HeaderOne />
        <SousGeneralContain>
            <MenuDeroulantOne />
            <PrincipalContain>
                <SousPrincipal>
                    <NormalSearch place-holder-search="Annee" :search-function="() => getAllStat()" 
                        :reactive-object="recherche" reactive-reference="annee" :liste-select="listeSelect"/>
                        <div class="col-lg-6 grid-margin stretch-card mx-auto special_stat generalContain">
                            <div class="card">
                                <div class="card-body">
                                    <H4CardTitle card-title="Vente"/>
                                    <CardDescription card-description="Statistique"/>
                                    <div class="table-responsive">
                                        <table class="table">
                                            <TableHead :title-header="['Quantite', 'Mois', 'Annee']"/>
                                            <tbody>
                                                <TableContain v-for="stat in allStat.data" 
                                                :liste-elements="[stat.quantiteLaptop, stat.mois, stat.annee]"/>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        <Bar
                            id="my-chart-id"
                            :options="chartOptions"
                            :data="chartData"
                        />
                    </div>
                </SousPrincipal>
                <FooterOne />
            </PrincipalContain>
        </SousGeneralContain>
    <button @click="exportToPDF">Exporter en PDF</button>
    </GeneralContain>
    <!-- <div ref="contentToExport">
      <h1>Mon Document PDF</h1>
      <p>Ceci est un exemple de contenu à exporter en PDF.</p>
    </div> -->
</template>
<style scoped>
    @import "@/assets/css/reeuse/liste.css";

    #my-chart-id {
        width: auto  !important;
        height: auto !important;
    }

    .special_stat {
        display: flex;
        flex-direction: column;
        gap: 75px;
        margin-left: 0 !important;
        height: auto;
    }
</style>