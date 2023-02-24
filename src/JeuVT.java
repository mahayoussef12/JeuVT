/*

import javax.swing.UIManager;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// classe qui crée une fenetre affichant de l'aide au joueur
class info extends JFrame
{
    private static final long serialVersionUID = 1L;// eliminer le warninig de serial version
    // Constructeur de la classe
    info()
    {
        String s="Ce jeu interactive est elaboree par\n F.Amine,etudiant en 3eme Annee ";
        s=s+"Maitrise informatique.\n Comment Jouer :\n1- Il faut d'abord cliquer sur le bouton Start";
        s=s+" puis dans le champs de saisie voud devez taper un nombre de 4 chiffres distinctes.\n2- Apres que tu saisie votre proposition ";
        s=s+"vous pouvez continuer en cliquant sur le bouton Retablir ";
        s=s+".\nSi vous voulez rejouer sans avoir la bonne solution il faut cliquer sur Stop puis Start .\n";
        s=s+"Si vous decidez de sortir du doit cliquer sur Exit .\n3- Si tu trouve le code secret tu ";
        s=s+"peut rejouer ou bien sortir.";
        JTextArea inf=new JTextArea(s);
        this.getContentPane().add(inf);
        this.setLocation(250,250);
        this.pack();
    }
}
// la classe qui contient tous le traitement du jeu
class JeuTV extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    // Declaration et initialisation des boutons
    JButton bInfo=new JButton("Info");
    JButton bStart=new JButton("Start");
    JButton bStop=new JButton("Stop"); JButton bExit=new JButton("Exit");

    JButton bRetab=new JButton("Rtablir");
    JButton bTest=new JButton("Tester le nombre");
    // Declaration et initialisation des JTextField et JTextArea
    JTextField essaie=new JTextField();
    JTextArea histo=new JTextArea(" Bonne Chance \n",20,30); // servira pour afficher l'historique
    JScrollPane scroll=new JScrollPane(histo);
    JLabel res=new JLabel();// affichage du resultat de la tentative
    JLabel nbrTent=new JLabel();// nombre de tentative
    int code=0;// variable qui contiendra le code à chercher
    int nbr_chiffre=0,NbrEssaie=0;
    // Constructeur
    JeuTV()
    {
        super("Jeu VT");
        // intialiser le theme de l'application par celui du systeme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        essaie.setMaximumSize(new Dimension(10,20));essaie.setEnabled(false);
        JLabel lTitle=new JLabel("Tester Votre Intelligence");
        JLabel lInvite=new JLabel("Tapez Votre Essaie 4 chiffres differents :");
        JLabel lRes=new JLabel("Votre Nombre est :");
        JLabel nbrEss=new JLabel("==> Le nombre d'essaie est :");
        JPanel menu =new JPanel();menu.setLayout(new GridLayout(1,4,10,10));
        bInfo.addActionListener(this);bStart.addActionListener(this);
        bStop.addActionListener(this);bExit.addActionListener(this);
        bRetab.addActionListener(this);bTest.addActionListener(this);
        bStop.setEnabled(false);bRetab.setEnabled(false);bTest.setEnabled(false);
        menu.add(bInfo);menu.add(bStart);menu.add(bStop);menu.add(bExit);
        JPanel p1 =new JPanel();JPanel p2 =new JPanel();JPanel p3 =new JPanel();
        JPanel p4 =new JPanel();JPanel p5 =new JPanel();p1.add(lTitle);
        p2.setLayout(new GridLayout(1,2,10,20));p2.add(lInvite);p2.add(essaie);
        p3.setLayout(new GridLayout(1,2,20,20));p3.add(bRetab);p3.add(bTest);
        p4.setLayout(new GridLayout(1,2,20,20));p4.add(lRes);p4.add(res);
        p5.setLayout(new GridLayout(1,2,20,20));p5.add(nbrEss);p5.add(nbrTent);
        JPanel p6=new JPanel(new FlowLayout(FlowLayout.CENTER));p6.add(scroll);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        this.getContentPane().add(menu);this.getContentPane().add(p1);
        this.getContentPane().add(p2);this.getContentPane().add(p3);
        this.getContentPane().add(p4);this.getContentPane().add(p5);
        this.getContentPane().add(p6);
        setLocation(200,100);
        this.setSize(450,400);
        setVisible(true);
    }
    // Méthode pour construire le code à chercher
    void ConstructionCode()
    {
        int n=10;
        int []numbers={0,1,2,3,4,5,6,7,8,9};
        while(nbr_chiffre<4)
        {
            int var=(int)(Math.random()*n);// choisir par hasard un entier = rang de l'entier à tirer du tableau
            code=(code*10)+numbers[var];// construire le code
            numbers[var]=numbers[n-1];// remplacer l'entier choisit par le dernier entier dans le tableau
            n--;// diminuer n(taille du tableau)
            nbr_chiffre++;
        }// repeter le processus jusqua avoir 4 chiffres diffrents
    }
    // Méthode pour tester le code saisie par l'utilisateur
    int Test(int code,int p)
    {
        int x=code;
        int t=0;
        int v=0;
        int i=3,j=3;
        while(i>=0&&j>=0)
        {
            int  prop=p;
            while(j>=0)
            {
                int divp=prop/(int)Math.pow(10,j);// extraire les entier en commencant par le gauche,
                // un par un
                int divc=x/(int)Math.pow(10,i);// la meme chose mais sur le code secret
                if(divp==divc)// tester s'il y a égalité de 2 entiers
                {
                    if(i==j)// tester s'ils ont le meme rang ou emplacement dans le code
                        t++;
                    else
                        v++;
                    break;
                }
                prop=prop%(int)(Math.pow(10,j));// prendre les 3 derniers chiffres du code saisie par
                // le user
                j--;// diminuer le nombre de boucle
            }
            j=3;// reinitialiser j à 3 pour retester le code saisie par le user(complet)par
            x=x%(int)(Math.pow(10,i));// le code secret incomplet du premier chiffre.
            i--;
        }
        return((t*10)+v);// retourner un entier indiquant le nombre de T et de V
    }
    // Méthode pour tester la validité du code saisie avant de le comparer avec le code secret"CS"
    boolean estValide(int prop)
    {
        int [] tab=new int[4];
        int p=prop;
        boolean trouve=false;
        if(p<123)
            trouve=true;
        for(int i=3;i>=0;i--)
        {
            tab[i]=p/(int)(Math.pow(10,i));
            p=p%(int)(Math.pow(10,i));
        }
        int j=0;
        int var=tab[j];
        while(!trouve&&j<3)
        {
            for(int i=j+1;i<4;i++)
            {
                if(var==tab[i])
                {trouve=true;
                    break;}
            }
            j++;
            var=tab[j];
        }
        if (!trouve)
            return true;
        else return false;
    }
    // inplementer la méthode actionPerformed
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bInfo)
        {
            info i=new info();
            i.setVisible(true);
        }
        else if(e.getSource()==bExit)
        {dispose();}
        else if(e.getSource()==bStart){bStart.setEnabled(false);
            bStop.setEnabled(true);bRetab.setEnabled(true);bTest.setEnabled(true);
            essaie.setEnabled(true);ConstructionCode();}
        else if(e.getSource()==bStop){bStop.setEnabled(false);bStart.setEnabled(true);
            bRetab.setEnabled(false);bTest.setEnabled(false);essaie.setEnabled(false);
            code=0;NbrEssaie=0;nbr_chiffre=0;essaie.setText("");res.setText("");nbrTent.setText("");}
        else if(e.getSource()==bRetab){essaie.setText("");}
        else if(e.getSource()==bTest)
        {
            if(!estValide(Integer.parseInt(essaie.getText()))) res.setText("Erreur ,repetition des chiffres");
            else
            {
                if((Test(code,Integer.parseInt(essaie.getText()))/10)<4)
                {
                    res.setText(""+Test(code,Integer.parseInt(essaie.getText()))/10+" T "+Test(code,Integer.parseInt(essaie.getText()))%10+" V ");
                    histo.append(essaie.getText()+" : "+res.getText()+"\n");}
                else{res.setText("BRAVO, Tu a reussi a trouver le code");
                    bStart.setEnabled(true);bStop.setEnabled(false);bRetab.setEnabled(false);
                    bTest.setEnabled(false);essaie.setEnabled(false);essaie.setText("");}
                NbrEssaie++;
                nbrTent.setText(""+NbrEssaie);}
        }
    }
    // Méthode main
    public static void main(String[]args)
    {
        JeuTV jtv=new JeuTV();
    }
}
*/
