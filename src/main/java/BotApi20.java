import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class BotApi20 extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {


                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("трассы от простого к сложному");
                message.setReplyMarkup(getCardKeyboard());
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {

            }

        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            int message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.contains("-")) {
                String [] index = call_data.split("-");
                int route = Integer.valueOf(index[0]);
                int attempt = Integer.valueOf(index[1]);

                String answer = call_data + " "+ route+ " "+ attempt;
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(message_id)
                        .setText(answer);
                new_message.setReplyMarkup(getCardKeyboard());
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private InlineKeyboardMarkup getCardKeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(new InlineKeyboardButton().setText("1. Белая угроза (углов нет)").setCallbackData("ничего"));
        rowsInline.add(rowInline1);

        List<InlineKeyboardButton> rowInline11 = new ArrayList<>();
        rowInline11.add(new InlineKeyboardButton().setText("0").setCallbackData("1-0"));
        rowInline11.add(new InlineKeyboardButton().setText("1").setCallbackData("1-1"));
        rowInline11.add(new InlineKeyboardButton().setText("2").setCallbackData("1-2"));
        rowInline11.add(new InlineKeyboardButton().setText("3>").setCallbackData("1-3"));
        rowsInline.add(rowInline11);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public String getBotUsername() {
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "rus_climbing_bot";
    }

    @Override
    public String getBotToken() {
        return "529707460:AAHm3FTTlpZVKt0wPiN9XBeN7ZYHero1CVA";
    }
}
