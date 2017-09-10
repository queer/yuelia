package stream.yuelia;

import lombok.Getter;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.EventListener;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

/**
 * @author amy
 * @since 9/8/17.
 */
public final class Yuelia {
    @Getter
    private JDA jda;
    
    @Getter
    private final Logger logger = Logger.getLogger("yuelia");
    
    private Yuelia() {
    }
    
    public static void main(final String[] args) {
        new Yuelia().start();
    }
    
    private void start() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(System.getenv("YUELIA_DISCORD_TOKEN"))
                    .addEventListener((EventListener) event -> {
                        if(event instanceof ReadyEvent) {
                            getLogger().info("yuelia started up and ready to go");
                        } else if(event instanceof GuildMessageReceivedEvent) {
                            final Message message = ((GuildMessageReceivedEvent) event).getMessage();
                            if(message.getGuild().getId().equalsIgnoreCase("267500017260953601")) {
                                if(message.getContent().trim().equalsIgnoreCase("!what")) {
                                    final EmbedBuilder builder = new EmbedBuilder();
                                    builder.setTitle("yuelia | amybot for twitch", "https://yuelia.stream")
                                            .addField("What?", "yuelia is amybot for Twitch.TV. There is no guarantee of when she'll be ready.", false);
                                    message.getChannel().sendMessage(builder.build()).queue();
                                } else if(message.getContent().trim().equalsIgnoreCase("!help")) {
                                    message.getChannel().sendMessage("If you're trying to use <@251930037673132032>, type `amy!help`.").queue();
                                }
                            }
                        }
                    })
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setGame(Game.of("!what | amybot for twitch"))
                    .buildAsync();
        } catch(final LoginException | RateLimitedException e) {
            throw new IllegalStateException(e);
        }
    }
}
