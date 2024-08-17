package umg.progra2.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import umg.progra2.Creds.credencialesBot;
import umg.progra2.Persona.estudianteClass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class tareaBot extends TelegramLongPollingBot {

    credencialesBot botCreds = new credencialesBot();
    String nombreBot = botCreds.name_bot_request();
    String tokenBot = botCreds.token_bot_request();

    estudianteClass studentdata = new estudianteClass();
    String carnetAlumno = studentdata.numeroCarnet_request();
    String nombreAlumno = studentdata.nombreAlumno_request();
    int semestreAlumno = studentdata.numeroSemestre_request();

    LocalDateTime ahorita = LocalDateTime.now();
    DateTimeFormatter formatearFecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM");
    String fechaConFormato = ahorita.format(formatearFecha);


    @Override
    public String getBotUsername() {
        return nombreBot;
    }

    @Override
    public String getBotToken() {
        return tokenBot;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickName = update.getMessage().getFrom().getUserName();


        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.toLowerCase().contains("/info")){
                sendPhoto(chat_id,"https://scontent.fgua3-3.fna.fbcdn.net/v/t39.30808-6/391602617_6989747894403173_2664912256259389601_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=4Vm__0p6XfQQ7kNvgHjenhc&_nc_ht=scontent.fgua3-3.fna&oh=00_AYBPyHN_f8Gm67miYSI17BwrU5PrU98upacKeb8MHgx_pA&oe=66C6AD54", "Fotografía de Rony Carrillo en el 2023");
                sendText(chat_id,"Hola " + nombre + " bienvenido al soporte tecnico de Wayn3 Industries.\n\n" + "\nMi creador es: \n" + nombreAlumno + "\n\nSu numero de carnet es el: \n" + carnetAlumno + "\n\nSe encuentra en el semestre numero: \n" + semestreAlumno);
            }
            if (message_text.toLowerCase().contains("/progra")) {
                sendText(chat_id, "Para mi la programación siempre ha sido algo muy interesante, algo par alo que me considero bueno pero no expoto mis capacitades con todo lo que podría, no dedico el tiempo que me gustaría ha programar, por ejemplo esta tarea la realizé mientras recibo otra clase, pero me hubiera encantado tener la convicción de haber tomado las tardes o noches de mi semana para poder hacer este bot algo sorpendente y desafiante para mi.\n\nPero dejando eso de lado el curso de programación desde muy pequeño fue el más interante para mi, la fomra en la que puedo dar libertad a mi imaginación y el desarrollo de mi lógica es la clase que más me nutre, por lo que en general es de mis clases favoritas.");
            }
            if (message_text.toLowerCase().contains("/hola")) {
                sendText(chat_id, "Hola " + nombre + " hoy es " + fechaConFormato);
            }
            if (message_text.startsWith("/cambio")) {
                String[] parts = message_text.split(" ");
                if (parts.length == 2) {
                    double euros = Double.parseDouble(parts[1]);
                    double tipoCambio = 8.52; // Tipo de cambio actual
                    double quetzales = euros * tipoCambio;
                    sendText(chat_id, "Su cambio es: Q" + quetzales);
                } else {
                    sendText(chat_id, "Por favor, usa el formato /cambio <numero de cambio>");
                }

            }

        }


    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public void sendPhoto(Long who, String photoUrl, String caption) {
        SendPhoto sp = SendPhoto.builder()
                .chatId(who.toString()) // A quién le estamos enviando la foto
                .photo(new InputFile(photoUrl)) // URL de la foto
                .caption(caption)
                .build();
        try {
            execute(sp); // Enviando la foto
        } catch (TelegramApiException e) {
            throw new RuntimeException(e); // Cualquier error será impreso aquí
        }
    }

}
