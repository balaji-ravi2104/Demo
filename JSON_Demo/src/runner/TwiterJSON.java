package runner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwiterJSON {
	public static void main(String[] args) {
		JSONObject object = new JSONObject("{\n"
				+ "  \"data\": [\n"
				+ "    {\n"
				+ "      \"text\": \"We believe the best future version of our API will come from building it with YOU. Here’s to another great year with everyone who builds on the Twitter platform. We can’t wait to continue working with you in the new year. https://t.co/yvxdK6aOo2\",\n"
				+ "      \"edit_history_tweet_ids\": [\n"
				+ "        \"1212092628029698048\"\n"
				+ "      ],\n"
				+ "      \"lang\": \"en\",\n"
				+ "      \"in_reply_to_user_id\": \"2244994945\",\n"
				+ "      \"entities\": {\n"
				+ "        \"urls\": [\n"
				+ "          {\n"
				+ "            \"start\": 222,\n"
				+ "            \"end\": 245,\n"
				+ "            \"url\": \"https://t.co/yvxdK6aOo2\",\n"
				+ "            \"expanded_url\": \"https://twitter.com/LovesNandos/status/1211797914437259264/photo/1\",\n"
				+ "            \"display_url\": \"pic.twitter.com/yvxdK6aOo2\",\n"
				+ "            \"media_key\": \"16_1211797899316740096\"\n"
				+ "          }\n"
				+ "        ],\n"
				+ "        \"annotations\": [\n"
				+ "          {\n"
				+ "            \"start\": 42,\n"
				+ "            \"end\": 44,\n"
				+ "            \"probability\": 0.5359,\n"
				+ "            \"type\": \"Other\",\n"
				+ "            \"normalized_text\": \"API\"\n"
				+ "          },\n"
				+ "          {\n"
				+ "            \"start\": 144,\n"
				+ "            \"end\": 150,\n"
				+ "            \"probability\": 0.9832,\n"
				+ "            \"type\": \"Other\",\n"
				+ "            \"normalized_text\": \"Twitter\"\n"
				+ "          }\n"
				+ "        ]\n"
				+ "      },\n"
				+ "      \"author_id\": \"2244994945\",\n"
				+ "      \"referenced_tweets\": [\n"
				+ "        {\n"
				+ "          \"type\": \"replied_to\",\n"
				+ "          \"id\": \"1212092627178287104\"\n"
				+ "        }\n"
				+ "      ],\n"
				+ "      \"id\": \"1212092628029698048\",\n"
				+ "      \"public_metrics\": {\n"
				+ "        \"retweet_count\": 7,\n"
				+ "        \"reply_count\": 3,\n"
				+ "        \"like_count\": 38,\n"
				+ "        \"quote_count\": 1\n"
				+ "      },\n"
				+ "      \"context_annotations\": [\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"29\",\n"
				+ "            \"name\": \"Events [Entity Service]\",\n"
				+ "            \"description\": \"Real world events. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1186637514896920576\",\n"
				+ "            \"name\": \" New Years Eve\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"29\",\n"
				+ "            \"name\": \"Events [Entity Service]\",\n"
				+ "            \"description\": \"Real world events. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1206982436287963136\",\n"
				+ "            \"name\": \"Happy New Year: It’s finally 2020 everywhere!\",\n"
				+ "            \"description\": \"Catch fireworks and other celebrations as people across the globe enter the new year.\\nPhoto via @GettyImages \"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"119\",\n"
				+ "            \"name\": \"Holiday\",\n"
				+ "            \"description\": \"Holidays like Christmas or Halloween\"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1186637514896920576\",\n"
				+ "            \"name\": \" New Years Eve\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"119\",\n"
				+ "            \"name\": \"Holiday\",\n"
				+ "            \"description\": \"Holidays like Christmas or Halloween\"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1206982436287963136\",\n"
				+ "            \"name\": \"Happy New Year: It’s finally 2020 everywhere!\",\n"
				+ "            \"description\": \"Catch fireworks and other celebrations as people across the globe enter the new year.\\nPhoto via @GettyImages \"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"30\",\n"
				+ "            \"name\": \"Entities [Entity Service]\",\n"
				+ "            \"description\": \"Entity Service top level domain, every item that is in Entity Service should be in this domain\"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"781974596752842752\",\n"
				+ "            \"name\": \"Services\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"47\",\n"
				+ "            \"name\": \"Brand\",\n"
				+ "            \"description\": \"Brands and Companies\"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"10045225402\",\n"
				+ "            \"name\": \"Twitter\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"131\",\n"
				+ "            \"name\": \"Unified Twitter Taxonomy\",\n"
				+ "            \"description\": \"A taxonomy of user interests. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"10045225402\",\n"
				+ "            \"name\": \"Twitter\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"131\",\n"
				+ "            \"name\": \"Unified Twitter Taxonomy\",\n"
				+ "            \"description\": \"A taxonomy of user interests. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"847868745150119936\",\n"
				+ "            \"name\": \"Family & relationships\",\n"
				+ "            \"description\": \"Hobbies and interests\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"131\",\n"
				+ "            \"name\": \"Unified Twitter Taxonomy\",\n"
				+ "            \"description\": \"A taxonomy of user interests. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1196446161223028736\",\n"
				+ "            \"name\": \"Social media\"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"29\",\n"
				+ "            \"name\": \"Events [Entity Service]\",\n"
				+ "            \"description\": \"Real world events. \"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1206982436287963136\",\n"
				+ "            \"name\": \"Happy New Year: It’s finally 2020 everywhere!\",\n"
				+ "            \"description\": \"Catch fireworks and other celebrations as people across the globe enter the new year.\\nPhoto via @GettyImages \"\n"
				+ "          }\n"
				+ "        },\n"
				+ "        {\n"
				+ "          \"domain\": {\n"
				+ "            \"id\": \"119\",\n"
				+ "            \"name\": \"Holiday\",\n"
				+ "            \"description\": \"Holidays like Christmas or Halloween\"\n"
				+ "          },\n"
				+ "          \"entity\": {\n"
				+ "            \"id\": \"1206982436287963136\",\n"
				+ "            \"name\": \"Happy New Year: It’s finally 2020 everywhere!\",\n"
				+ "            \"description\": \"Catch fireworks and other celebrations as people across the globe enter the new year.\\nPhoto via @GettyImages \"\n"
				+ "          }\n"
				+ "        }\n"
				+ "      ],\n"
				+ "      \"created_at\": \"2019-12-31T19:26:16.000Z\",\n"
				+ "      \"attachments\": {\n"
				+ "        \"media_keys\": [\n"
				+ "          \"16_1211797899316740096\"\n"
				+ "        ]\n"
				+ "      },\n"
				+ "      \"possibly_sensitive\": false\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"includes\": {\n"
				+ "    \"tweets\": [\n"
				+ "      {\n"
				+ "        \"text\": \"These launches would not be possible without the feedback you provided along the way, so THANK YOU to everyone who has contributed your time and ideas. Have more feedback? Let us know ⬇️ https://t.co/Vxp4UKnuJ9\",\n"
				+ "        \"edit_history_tweet_ids\": [\n"
				+ "          \"1212092627178287104\"\n"
				+ "        ],\n"
				+ "        \"lang\": \"en\",\n"
				+ "        \"in_reply_to_user_id\": \"2244994945\",\n"
				+ "        \"entities\": {\n"
				+ "          \"urls\": [\n"
				+ "            {\n"
				+ "              \"start\": 187,\n"
				+ "              \"end\": 210,\n"
				+ "              \"url\": \"https://t.co/Vxp4UKnuJ9\",\n"
				+ "              \"expanded_url\": \"https://twitterdevfeedback.uservoice.com/forums/921790-twitter-developer-labs\",\n"
				+ "              \"display_url\": \"twitterdevfeedback.uservoice.com/forums/921790-…\",\n"
				+ "              \"status\": 200,\n"
				+ "              \"title\": \"Updates on our feedback channels\",\n"
				+ "              \"description\": \"We build our developer platform in the open, with your input and feedback. Over the past year, hearing directly from you and the users of your apps has helped us build developer products that cater to the use case you helped us identify. We want to make this the way we build products, and moving forward, we are consolidating our feedback channels. Meeting you where you are Effective today, we are going to retire our UserVoice feedback channel in favor of more frequent direct engagements with y...\",\n"
				+ "              \"unwound_url\": \"https://twittercommunity.com/t/updates-on-our-feedback-channels/169706\"\n"
				+ "            },\n"
				+ "            {\n"
				+ "              \"start\": 287,\n"
				+ "              \"end\": 310,\n"
				+ "              \"url\": \"https://t.co/Vxp4UKnuJ9\",\n"
				+ "              \"expanded_url\": \"https://twitterdevfeedback.uservoice.com/forums/921790-twitter-developer-labs\",\n"
				+ "              \"display_url\": \"twitterdevfeedback.uservoice.com/forums/921790-…\",\n"
				+ "              \"status\": 200,\n"
				+ "              \"title\": \"Updates on our feedback channels\",\n"
				+ "              \"description\": \"We build our developer platform in the open, with your input and feedback. Over the past year, hearing directly from you and the users of your apps has helped us build developer products that cater to the use case you helped us identify. We want to make this the way we build products, and moving forward, we are consolidating our feedback channels. Meeting you where you are Effective today, we are going to retire our UserVoice feedback channel in favor of more frequent direct engagements with y...\",\n"
				+ "              \"unwound_url\": \"https://twittercommunity.com/t/updates-on-our-feedback-channels/169706\"\n"
				+ "            },\n"
				+ "            {\n"
				+ "              \"start\": 123,\n"
				+ "              \"end\": 200,\n"
				+ "              \"url\": \"https://t.co/Vxp4UKnuJ9\",\n"
				+ "              \"expanded_url\": \"https://twitterdevfeedback.uservoice.com/forums/921790-twitter-developer-labs\",\n"
				+ "              \"display_url\": \"twitterdevfeedback.uservoice.com/forums/921790-…\",\n"
				+ "              \"status\": 200,\n"
				+ "              \"title\": \"Updates on our feedback channels\",\n"
				+ "              \"description\": \"We build our developer platform in the open, with your input and feedback. Over the past year, hearing directly from you and the users of your apps has helped us build developer products that cater to the use case you helped us identify. We want to make this the way we build products, and moving forward, we are consolidating our feedback channels. Meeting you where you are Effective today, we are going to retire our UserVoice feedback channel in favor of more frequent direct engagements with y...\",\n"
				+ "              \"unwound_url\": \"https://twittercommunity.com/t/updates-on-our-feedback-channels/169706\"\n"
				+ "            }\n"
				+ "          ]\n"
				+ "        },\n"
				+ "        \"author_id\": \"2244994945\",\n"
				+ "        \"referenced_tweets\": [\n"
				+ "          {\n"
				+ "            \"type\": \"replied_to\",\n"
				+ "            \"id\": \"1212092626247110657\"\n"
				+ "          }\n"
				+ "        ],\n"
				+ "        \"id\": \"1212092627178287104\",\n"
				+ "        \"public_metrics\": {\n"
				+ "          \"retweet_count\": 2,\n"
				+ "          \"reply_count\": 1,\n"
				+ "          \"like_count\": 19,\n"
				+ "          \"quote_count\": 0\n"
				+ "        },\n"
				+ "        \"created_at\": \"2019-12-31T19:26:16.000Z\",\n"
				+ "        \"possibly_sensitive\": false\n"
				+ "      }\n"
				+ "    ]\n"
				+ "  }\n"
				+ "}");
		// System.out.println(object.toString(2));
//		JSONArray array = new JSONArray();
//		array.put(object.get("data"));

		JSONArray dataArray = object.getJSONArray("data");
//		System.out.println(dataArray.toString(2));

		JSONObject dataArrayObject1 = dataArray.getJSONObject(0);
		// System.out.println(dataArrayObject1.toString(2));

//		String text = dataArrayObject1.getString("text");
		// System.out.println(text);

//		JSONArray edit_history_tweet_ids = dataArrayObject1.getJSONArray("edit_history_tweet_ids");
//		System.out.println(edit_history_tweet_ids);
//		System.out.println(edit_history_tweet_ids.get(0));
//		System.out.println(dataArrayObject1.getJSONArray("edit_history_tweet_ids").get(0));

		JSONObject entities = dataArrayObject1.getJSONObject("entities");
		// System.out.println(entities.toString(2));
		JSONArray urls = entities.getJSONArray("urls");
		// System.out.println(urls.toString(2));

//		JSONObject urlsObject = urls.getJSONObject(0);
		// System.out.println(urlsObject.toString(2));
//		System.out.println(urlsObject.getInt("start"));
//		System.out.println(urlsObject.getString("media_key"));

		JSONArray annotations = entities.getJSONArray("annotations");
		// System.out.println(annotations.toString(2));

		JSONObject type = annotations.getJSONObject(1);
		//System.out.println(type.get("normalized_text"));

//		try {
//			
//			JSONArray data = object.getJSONArray("data");
//			JSONObject dataObject = data.getJSONObject(0); 
//
//			JSONArray contextAnnotations = dataObject.getJSONArray("context_annotations");
//
//			
//			for (int i = 0; i < contextAnnotations.length(); i++) {
//				JSONObject annotation = contextAnnotations.getJSONObject(i);
//
//				JSONObject domain = annotation.getJSONObject("domain");
//				String id = domain.getString("id");
//
//			
//				if (id.equals("119")) {
//					System.out.println(annotation.toString(2));
//					break;
//				}
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		
		try {
			JSONObject includesObject = object.getJSONObject("includes");
			//System.out.println(includesObject.toString(2));
			JSONArray tweetsArray = includesObject.getJSONArray("tweets");
			//System.out.println(tweetsArray.toString(2));
			JSONObject tweetArrayJSONObject = tweetsArray.getJSONObject(0);
			//System.out.println(tweetArrayJSONObject.toString(2));
			JSONObject tweetArrayEntitiesJSONObject = tweetArrayJSONObject.getJSONObject("entities");
			//System.out.println(tweetArrayEntitiesJSONObject.toString(2));
			
			JSONArray entitiesUrlsArray = tweetArrayEntitiesJSONObject.getJSONArray("urls");
			//System.out.println(entitiesUrlsArray.toString(2));
			for(int i=0;i<entitiesUrlsArray.length();i++) {
				JSONObject urlsArrayObject = entitiesUrlsArray.getJSONObject(i);
				//System.out.println(urlsArrayObject.toString(2));
				if(urlsArrayObject.getInt("start")>=100 && urlsArrayObject.getInt("end")<=350) {
					//System.out.println(urlsArrayObject.toString(2));
					System.out.println(urlsArrayObject.get("url"));
					System.out.println(urlsArrayObject.get("expanded_url"));
					System.out.println(urlsArrayObject.get("display_url"));
					System.out.println(urlsArrayObject.get("description"));
				}
			}
		}catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
