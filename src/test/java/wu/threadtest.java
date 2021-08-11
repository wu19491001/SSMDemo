package wu;


import com.wu.domain.User;

public class threadtest{
    private User user;

	public threadtest(User user) {
		this.user = user;
	}
	public String getUsername(){
		String username=user.getUsername();
		return username;
	}
}
