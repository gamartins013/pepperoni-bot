package pepperoni.bot.config;

public class TokenSecret {


    String tk1 = "MTA2ODc4NzMzNTYzMTc0MDk0OA.";
    String tk2 = "GAJH39.lNzm2plZdVacA9qlrPQ0WgIJhGH_WnVKK4WRUg";
    String tkf = tk1+tk2;
    private String token = tkf;
    public TokenSecret() {

        this.token = token;
    }
    public String getToken() {

        return token;
    }

}