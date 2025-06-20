package enums;

public enum CommunicationServices {
    PHONE_SERVICES("Услуги связи"),
    HOME_INTERNET("Домашний интернет"),
    INSTALLMENT_PLAN("Рассрочка"),
    DEBT("Задолженность");

    private String title;

    CommunicationServices(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

