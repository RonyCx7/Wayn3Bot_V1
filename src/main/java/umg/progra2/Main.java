package umg.progra2;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import umg.progra2.Creds.credencialesBot;
import umg.progra2.botTelegram.Bot;
import umg.progra2.botTelegram.PokemonBot;
import umg.progra2.botTelegram.tareaBot;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
            //Bot miBot = new Bot();
            //botApi.registerBot(miBot);

            //PokemonBot pokeDex = new PokemonBot();
            //botApi.registerBot(pokeDex);

            tareaBot hmBot = new tareaBot();
            botApi.registerBot(hmBot);
            System.out.println("El bot se est[a ejecutando!!!");

        }catch (Exception ex){
            System.out.println("Error al instanciar Telegram: " + ex.getMessage());
        }



    }
}