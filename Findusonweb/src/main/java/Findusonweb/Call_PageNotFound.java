package Findusonweb;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

public class Call_PageNotFound extends PageNotFound_By_Sir
{
	@Test
	public void test() throws MalformedURLException, InterruptedException
	{
		PageNotFound_By_Sir.find404error();
	}

}
