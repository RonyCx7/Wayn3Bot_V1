package umg.progra2.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLOutput;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Wayn3bot";
    }

    @Override
    public String getBotToken() {
        return "6351827444:AAF77BQw9wPvL3prcAqbT7rGFttDfOs3__Q";
    }



    //El método onUpdateReceived(Update update) de la clase Bot se usa para manejar todas las actualizaciones que el
    // bot recibe.
    // Dependiendo del tipo de actualización, se toman diferentes acciones.

    @Override
    public void onUpdateReceived(Update update) {

        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickName = update.getMessage().getFrom().getUserName();

        //Se verifica si la actualización contiene un mensaje y si ese mensaje tiene texto.
        //Luego se procesa el contenido del mensaje y se responde según el caso.
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.toLowerCase().equals("hola")){
                sendText(chat_id,"Hola " + nombre + "bienvenido al soporte tecnico de Wayn3 Industries.");
            }
            if (message_text.toLowerCase().contains("adios")) {
                sendText(chat_id, "Tenga un excelente dia. " + nombre);
                //System.out.println("User id: " + chat_id + " Message: " + message_text);
            }
            if (chat_id == 1232386900){
                sendText(chat_id,"Que onda Rony, tenes una llamada");
            }


            System.out.println(chat_id + " Hola " + nickName + "Tu nombre es: " + nombre + " " + apellido);
            System.out.println("Enviaste: " + message_text);

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
}
