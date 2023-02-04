package pepperoni.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import pepperoni.bot.config.TokenSecret;
import pepperoni.bot.events.InteractionEventListener;
import pepperoni.bot.events.MessageEventListener;
import pepperoni.bot.events.ReadyEventListener;

public class Main {

    public static void main(String[] args) {

        TokenSecret token = new TokenSecret();

        JDABuilder jdaBuilder = JDABuilder.createDefault(token.getToken());

        JDA jda = jdaBuilder.setActivity(Activity.playing("curioso ne"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(
                        new ReadyEventListener(),
                        new MessageEventListener(),
                        new InteractionEventListener())
                .build();

        jda.upsertCommand("ping", "e a porra de um comando que retorna pong").setGuildOnly(true).queue();
        jda.upsertCommand("offend", "Vai soltar umas ofensas aleatorias ").setGuildOnly(true).queue();
        jda.updateCommands().addCommands(
                Commands.slash("ban", "Podera banir alguem caso precise")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS))
                        .setGuildOnly(true)
                        .addOption(OptionType.USER, "user", "Nome do usuario para banir", true)
                        .addOption(OptionType.STRING, "reason", "Motivo do banimento")
        ).queue();
    }


}