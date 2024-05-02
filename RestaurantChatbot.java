import java.util.*;
import java.time.LocalTime;

public class RestaurantChatbot {

    // Define responses
    static Map<String, String[]> responses = new HashMap<>();
    
    static {
        responses.put("greeting_morning", new String[]{"Good morning! Welcome to our restaurant.", "Good morning! How can I assist you today?"});
        responses.put("greeting_afternoon", new String[]{"Good afternoon! Welcome to our restaurant.", "Good afternoon! How can I assist you today?"});
        responses.put("greeting_evening", new String[]{"Good evening! Welcome to our restaurant.", "Good evening! How can I assist you today?"});
        responses.put("menu_pizza", new String[]{"Our pizza is priced at ₹700."});
        responses.put("menu_pasta", new String[]{"Our pasta is priced at ₹800."});
        responses.put("menu_burgers", new String[]{"Our burgers are priced at ₹500."});
        responses.put("menu_salads", new String[]{"Our salads are priced at ₹400."});
        responses.put("menu_seafood", new String[]{"Our seafood dish is priced at ₹1000."});
        responses.put("menu_steaks", new String[]{"Our steaks are priced at ₹1500."});
        responses.put("menu_vegetarian", new String[]{"Our vegetarian dish is priced at ₹600."});
        responses.put("hours", new String[]{"We are open from 11:00 AM to 10:00 PM every day.", "Our restaurant hours are from 11:00 AM to 10:00 PM, seven days a week."});
        responses.put("location", new String[]{"We are located at 123 Main Street, City, State.", "Our restaurant is situated at the corner of First Avenue and Elm Street."});
        responses.put("thanks", new String[]{"You're welcome! Enjoy your meal.", "No problem! Let me know if you need anything else."});
        responses.put("goodbye", new String[]{"Goodbye! Have a great day.", "Thanks for visiting us! Come back soon."});
    }

    // Streamlit UI
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Restaurant Chatbot");
        System.out.println("Welcome to our restaurant! How can I assist you today?");
        
        List<String> chatHistory = new ArrayList<>();
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                break;
            }
            chatHistory.add("You: " + userInput);
            String botResponse = getBotResponse(userInput);
            chatHistory.add("Bot: " + botResponse);
            displayChatHistory(chatHistory);
        }
        
        scanner.close();
    }

    // Function to generate bot response
    static String getBotResponse(String userInput) {
        userInput = userInput.toLowerCase();
        LocalTime currentTime = LocalTime.now();

        // Determine the time of day
        String timeOfDay;
        if (currentTime.isBefore(LocalTime.parse("12:00:00"))) {
            timeOfDay = "morning";
        } else if (currentTime.isBefore(LocalTime.parse("18:00:00"))) {
            timeOfDay = "afternoon";
        } else {
            timeOfDay = "evening";
        }

        if (userInput.contains("hello") || userInput.contains("hi")) {
            return randomResponse("greeting_" + timeOfDay);
        } else if (userInput.contains("menu") || userInput.contains("food")) {
            return "Our menu items include pizza, pasta, burgers, salads, seafood, steaks, and vegetarian dishes.";
        } else if (userInput.contains("price")) {
            return "Please specify the item you want to know the price for.";
        } else if (userInput.contains("hours") || userInput.contains("open")) {
            return randomResponse("hours");
        } else if (userInput.contains("location") || userInput.contains("address")) {
            return randomResponse("location");
        } else if (userInput.contains("thank")) {
            return randomResponse("thanks");
        } else if (userInput.contains("bye")) {
            return randomResponse("goodbye");
        } else {
            for (String item : responses.keySet()) {
                if (userInput.contains(item.split("_")[1])) {
                    return responses.get(item)[0];
                }
            }
        }
        return "I'm sorry, I didn't understand that. How can I assist you?";
    }

    // Function to display chat history
    static void displayChatHistory(List<String> chatHistory) {
        for (String message : chatHistory) {
            System.out.println(message);
        }
    }

    // Helper function to get a random response from an array
    static String randomResponse(String key) {
        String[] options = responses.get(key);
        Random rand = new Random();
        return options[rand.nextInt(options.length)];
    }
}

// Welcome to our restaurant! How can I assist you today?
// You: Hi
// Bot: Good morning! How can I assist you today?
// You: What's on the menu?
// Bot: Our menu items include pizza, pasta, burgers, salads, seafood, steaks, and vegetarian dishes.
// You: How much is the pasta?
// Bot: Our pasta is priced at ₹800.
// You: What are your hours?
// Bot: Our restaurant hours are from 11:00 AM to 10:00 PM, seven days a week.
// You: Where are you located?
// Bot: Our restaurant is situated at the corner of First Avenue and Elm Street.
// You: Thanks!
// Bot: You're welcome! Enjoy your meal.
// You: Bye
// Bot: Goodbye! Have a great day.
