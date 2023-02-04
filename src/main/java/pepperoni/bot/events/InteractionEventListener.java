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
            case "ban":
                if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                    event.reply("Rapaz você é menino não pode banir membros").queue();
                    break;
                }
                User target = event.getOption("user", OptionMapping::getAsUser);

                Member member = event.getOption("user", OptionMapping::getAsMember);
                assert member != null;
                if (!event.getMember().canInteract(member)) {
                    event.reply("Esse cara ai não vai dar pra banir não em").queue();
                    break;
                }

                event.deferReply().queue();
                String reason = event.getOption("reason", OptionMapping::getAsString);
                assert target != null;
                AuditableRestAction<Void> action = Objects.requireNonNull(event.getGuild()).ban(target, 0, TimeUnit.NANOSECONDS);
                if (reason != null)
                    action = action.reason(reason);
                action.queue(v -> {
                    event.getHook().editOriginal("" + target.getAsTag() + " foi ** banido ** por " + event.getUser().getAsTag() + "!").queue();
                }, error -> {
                    event.getHook().editOriginal("Deu ruim ai cara tenta dnv").queue();
                    error.printStackTrace();
                });
                break;

        }


    }

}
