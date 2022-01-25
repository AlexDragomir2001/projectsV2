import java.util.*;

//clasa care personajele contului, obiect de tip Information si numarul de jocuri jucate de utilizator
public class Account {

    private Information informationAccount;
    private List<String> personaje;
    private List<String> nivele; // lista cu nivelele personajelor (adaugata de mine)
    private List<Long> experience;// list ca experianta personajelor (adaugata de mine)
    private List<String> profesii; // lista cu profesiile personajelor (adaugata de mine)
    private int playedGames;

    public Account(Information obj, int playedGames) {
        this.informationAccount = obj;
        this.personaje = new ArrayList<String>();
        this.nivele = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.profesii = new ArrayList<>();
        this.playedGames = playedGames;
    }

    public List<String> getNivele() {
        return nivele;
    }

    public List<Long> getExperience() {
        return experience;
    }

    public List<String> getProfesii() {
        return profesii;
    }

    public Information getInformationAccount() {
        return informationAccount;
    }

    public String getNameOfCharacter(int i) {
        return personaje.get(i);
    }

    public List<String> getPersonaje() {
        return personaje;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public void setNivele(String nivele) {
        this.nivele.add(nivele);
    }

    public void setExperience(Long experience) {
        this.experience.add(experience);
    }

    public void setProfesii(String profesii) {
        this.profesii.add(profesii);
    }

    public void setInformationAccount(Information obj) {
        this.informationAccount = obj;
    }

    public void setPersonaje(String personaje) {
        this.personaje.add(personaje);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return playedGames == account.playedGames && informationAccount.equals(account.informationAccount) && personaje.equals(account.personaje);
    }

    @Override
    public String toString() {
        return "Account{" +
                "obj=" + informationAccount +
                ", personaje=" + personaje +
                ", playedGames=" + playedGames +
                '}';
    }

    // clasa care contine numele lista cu jocurile favorite, obiect de tip Credentials, tara
    static class Information {

        private Credentials credentialsAccount;
        private List <String> favGames;
        private String name;
        private String country;

        public Information(Credentials obj, List<String> favGames, String name, String country) {
            this.credentialsAccount = obj;
            this.favGames = favGames;
            this.name = name;
            this.country = country;
        }

        public Credentials getCredentialsAccount() {
            return credentialsAccount;
        }

        public List<String> getFavGames() {
            return favGames;
        }

        public String getCountry() {
            return country;
        }

        public String getName() {
            return name;
        }

        public void setCredentialsAccount(Credentials obj) {
            this.credentialsAccount = obj;
        }

        public void setFavGames(List<String> favGames) {
            this.favGames = favGames;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Information that = (Information) o;
            return credentialsAccount.equals(that.credentialsAccount) && favGames.equals(that.favGames) && name.equals(that.name) && country.equals(that.country);
        }

        @Override
        public String toString() {
            return "Information{" +
                    "obj=" + credentialsAccount +
                    ", favGames=" + favGames +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

    }

}
