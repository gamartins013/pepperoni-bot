package pepperoni.bot.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import org.jetbrains.annotations.NotNull;
import pepperoni.bot.modules.OffendApi;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        System.out.println("[Console] O modulo de interacao ta funfante");
        switch (event.getName()) {
            case "ping":
                event.reply("Pega seu pong ai caralho").queue();
                break;
            case "offend":
                try {
                    String offendApi = new OffendApi().getOffend();
                    event.reply(offendApi).queue();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;


        }


    }

}
