package twitter;

import java.io.File;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;

public class TwitterConsumer {

    public static void main(final String[] args) throws Exception {

	final var log = LoggerFactory.getLogger(TwitterConsumer.class);

	final var currentDirectory = System.getProperty("user.dir");
	final var credentialsFilePath = currentDirectory + "\\src\\main\\resources\\twitter_credentials.json";
	final var credentialsFile = new File(credentialsFilePath);
	final var credentials = TwitterClient.OBJECT_MAPPER.readValue(credentialsFile, TwitterCredentials.class);
	final var twitterClient = new TwitterClient(credentials);

	log.info("Connection ready");

	twitterClient.addFilteredStreamRule("twitterapi", "#rugby");
	final Consumer<Tweet> consumer = x -> log.info(x.getText());
	twitterClient.startFilteredStream(consumer);
    }
}
