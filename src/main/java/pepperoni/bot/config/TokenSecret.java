package pepperoni.bot.config;

public class TokenSecret {

    private String token = System.getenv("TOKEN_BOT");
    public TokenSecret() {

        this.token = token;
    }
    public String getToken() {
        return token;
    }

}