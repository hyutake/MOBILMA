package Service.Admin;

import java.util.ArrayList;
import java.util.List;

import Constants.MovieRating;
import Constants.MovieStatus;
import Constants.MovieType;
import Controller.FileAccessController;
import Controller.FileAccess.MovieFileAccess;
import Model.Movie;
import Model.Review;
/** 
 * Initialises the Movie files with preset list of 10 movies and saves it as a .dat file
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class GenerateMovieFile {
    /**
     * Generates the movie files with the following 10 movies
     */
    public static void generateMovie() {
        List<Movie> movieList = new ArrayList<>();

        /* Movie 1 */
        String[] cast1 = {"Mayumi Tanaka", "Shuichi Ikeda", "Kaori Nazuka", "Kazuya Nakai", "Akemi Okamura",
         "Kappei Yamaguchi","Hiroaki Hirata"};
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Roy", "It is a musical theme movie. Dont try to watch it in terms of a story because there is none. Songs are good. I'm pretty much disappointed but it was worth one time watch I guess", 2));
        reviews.add(new Review("Kanish", "Great movie. They gave small hint about how shanks joined Roger. Almost 6-7 songs. Too much for a movie. It's the only disappointment", 4));
        reviews.add(new Review("Xavier", "This movie is a concert. It's great in the beginning until you get tired of it. The story is alright, not a lot of fights. The end you get a surprise GV", 3));
        movieList.add(new Movie(MovieType.BLOCKBUSTER, "One Piece Film:Red", MovieStatus.NOW_SHOWING,
         "Uta is a beloved singer, renowned for concealing her own identity when performing. Her voice is described as 'otherworldly'. Now, for the first time ever, Uta will reveal herself to the world at a live concert",
         "Goro Taniguchi", cast1, MovieRating.PG13, reviews, 12947));
        /* End of Movie 1 */

        /* Movie 2 */
        String[] cast2 = {"Dwayne Johnson", "Aldis Hodge", "Noah Centineo", "Sarah Shahi", "Marwan Kenzari"};
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(new Review("kocRehT", "Rest in peace to Her Majesty the Queen, Elizabeth II. A great woman who sat on the throne as long as anyone in British history. Sad she will never get to see #BlackAdam IN THEATERS OCT 21st", 5));
        reviews1.add(new Review("ThePebble", "Everyone knows Black Adam has only 2 weaknesses: lightning, and the death of a famous monarch. My condolences to the Royal Family during this difficult time. NEW #BlackAdam movie is great though!", 4));
        reviews1.add(new Review("BigA", "It's about drive, it's about power, we stay hungry we devour. MANAAAAAAAA", 5));
        movieList.add(new Movie(MovieType.IMAX, "Black Adam", MovieStatus.NOW_SHOWING,
         "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam. Nearly 5,000 years have passed, and Black Adam has gone from man to myth to legend. Now free, his unique form of justice, born out of rage, is challenged by modern-day heroes who form the Justice Society: Hawkman, Dr. Fate, Atom Smasher and Cyclone",
          "Jaume Collet-Serra", cast2, MovieRating.PG13, reviews1, 33982));
        /* End of Movie 2 */

        /* Movie 3 */
        String[] cast3 = {"Marty Bowen", "Wyck Godfrey", "Issac Klausner", "Robert Salerno", "Gabby Olivera"};
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(new Review("naidiri", "Just watched #SmileMovie :) and no one smile at me ever again I'm not kidding I'll fight you", 4));
        reviews2.add(new Review("Kaachan", "I have to say, this is one of the most unsettling movies I've seen in a long long time. One of the scenes made me shake in fear. True horror is back!", 5));
        reviews2.add(new Review("Katie Walsh", "As opaque and somewhat silly as the smiles that drive it", 5));
        movieList.add(new Movie(MovieType.BLOCKBUSTER, "Smile", MovieStatus.NOW_SHOWING,
         "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality",
          "Parker Finn", cast3, MovieRating.M18, reviews2, 32910));
        /* End of Movie 3 */
        
        /* Movie 4 */
        String[] cast4 = {"Sam Worthington", "Zoe Saldaña", "Sigourney Weaver", "Stephen Lang", "Cliff Curtis", "Joel David Moore"};
        List<Review> reviews3 = new ArrayList<>();
        // reviews.add(new Review("", "", 5));
        // reviews.add(new Review("", "", 5));
        // reviews.add(new Review("", "", 5));
        movieList.add(new Movie(MovieType.THREE_D, "Avatar: The Way of Water", MovieStatus.COMING_SOON,
         "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora. When an ancient threat resurfaces, Jake must fight a difficult war against the humans",
          "James Cameron", cast4, MovieRating.PG13, reviews3, 0));
        /* End of Movie 4 */

        /* Movie 5 */
        String[] cast5 = {"Brad Pitt", "Joey King", "Aaron Taylor-Johnson", "Brian Tyree Henry", "Andrew Koji", "Hiroyuki Sanada", "Michael Shannon", "Benito A. Martínez Ocasio", "Sandra Bullock"};
        List<Review> reviews4 = new ArrayList<>();
        reviews4.add(new Review("Maria", "What a blast, loved every minute, made me laugh out loud, jumped out of my seat a few times.  Brad Pitt's best movie for me. I especially loved tangerine & lemon - couldn't get enough of their bantering", 5));
        reviews4.add(new Review("Barry", "Loved it! Tangerine is indeed such a sophisticated fruit!", 5));
        reviews4.add(new Review("Johnston", "Great acting/characters, funny but with some poignant moments that brought a touch of reality to the many senseless killings, blood and gore galore, plot twists, non-stop action, amazing scenes, excellent music all contributing to a wild, fantastic ride to the very end", 4));
        movieList.add(new Movie(MovieType.BLOCKBUSTER, "Bullet Train", MovieStatus.NOW_SHOWING,
         "Ladybug is an unlucky assassin who's determined to do his job peacefully after one too many gigs has gone off the rails. Fate, however, may have other plans as his latest mission puts him on a collision course with lethal adversaries from around the globe -- all with connected yet conflicting objectives -- on the world's fastest train",
          "David Leitch", cast5, MovieRating.M18, reviews4, 33727));
        /* End of Movie 5 */

        /* Movie 6 */
        String[] cast6 = {"Jared Leto", "Matt Smith", "Adria Arjona", "Jared Harris", "Tyrese Gibson"};
        List<Review> reviews5 = new ArrayList<>();
        reviews5.add(new Review("Shinji", "Buying 20 more Morbius tickets so I can move freely throughout this screening to get the best angle. Shuffling around in the dark just like the man himself #MorbiusSweep", 5));
        reviews5.add(new Review("LinkAegis", "Just watched Morbius and gosh the tears in my eyes. The plot, the themes, the characters, the dynamics, the drama, the lore, the emotional moments...everything was garbage from beginning to end", 2));
        reviews5.add(new Review("gourilla_", "I didn't just watch Morbius, I sat my white ass down and listened", 5));
        movieList.add(new Movie(MovieType.BLOCKBUSTER, "Morbius", MovieStatus.END_OF_SHOWING,
        "Dangerously ill with a rare blood disorder and determined to save others from the same fate, Dr. Morbius attempts a desperate gamble. While at first it seems to be a radical success, a darkness inside of him is soon unleashed.",
          "Daniel Espinosa", cast6, MovieRating.PG13, reviews5, 28001));
        /* End of Movie 6 */

        /* Movie 7 */
        String[] cast7 = {"Millie Bobby Brown, Henry Cavill, Louis Partridge, Susie Wokoma, Adeel Akhtar, Helena Bonham Carter, David Thewlis, Sharon Duncan-Brewster"};
        List<Review> reviews6 = new ArrayList<>();
        reviews6.add(new Review("Neil", "This is just what we need, a truly fun and enjoyable sequel to the 2020 original.", 4));
        // reviews6.add(new Review("Idan", "If you were a fan of the first, like I was, you should enjoy this second outing...", 4));
        reviews6.add(new Review("Pranay", "Enola Holmes 2 is too concerned with chases, romance and flattering the target audience to even consider challenging anyone's puzzle-solving abilities", 2));
        movieList.add(new Movie(MovieType.IMAX, "Enola Holmes 2", MovieStatus.NOW_SHOWING,
        "Now a detective-for-hire, Enola Holmes takes on her first official case to find a missing girl as the sparks of a dangerous conspiracy ignite a mystery that requires the help of friends - and Sherlock himself - to unravel",
          "Harry Bradbeer", cast7, MovieRating.PG13, reviews6, 19980));
        /* End of Movie 7 */

        /* Movie 8 */
        String[] cast8 = {"Daniel Radcliffe, Evan Rachel Wood, Rainn Wilson, Toby Huss, Arturo Castro, Julianne Nicholson"};
        List<Review> reviews7 = new ArrayList<>();
        reviews7.add(new Review("Charles", "I used to love Weird Al, and would buy all his tapes when they came out. He has a childish inventiveness that even though often aggravating, is a wonderful thing.", 4));
        reviews7.add(new Review("Martin", "I just finished watching and I am actually blown away. Having it written as a parody is just absolutely genius. This is hands down the best biopic I have ever seen", 5));
        reviews7.add(new Review("Mason", "Weird: The Al Yankovic Story is a beautiful celebration of all things weird that stays true to its subjects personality with a hilarious lampooning of the standard biopic formula, filled with really funny inaccuracy and plenty of delightful surprises", 5));
        movieList.add(new Movie(MovieType.IMAX, "Weird: The Al Yankovic Story", MovieStatus.PREVIEW,
        "Explores every facet of Yankovic's life, from his meteoric rise to fame with early hits like 'Eat It' and 'Like a Surgeon' to his torrid celebrity love affairs and famously depraved lifestyle",
          "Eric Appel", cast8, MovieRating.NC16, reviews7, 13287));
        /* End of Movie 8 */

        /* Movie 9 */
        String[] cast9 = {"George Clooney, Julia Roberts, Kaitlyn Dever, Billie Lourd, Maxime Bouttier, Lucas Bravo"};
        List<Review> reviews8 = new ArrayList<>();
        reviews8.add(new Review("Jordan", "Ticket to Paradise is the clear example that the trailer is better than the movie itself. The script does not bring anything new to romantic comedies, it rushes to take the already known formula and fill the plot with the typical clichés", 1));
        reviews8.add(new Review("Kryz", "This is a loose remake of a French comedy with Dany Boon. That one was much funnier, with a better script.", 1));
        reviews8.add(new Review("Mason", "Ticket to Paradise follows a very familiar formula that has very few surprises in its narrative but that works as a nice reminder as to why this formula works so well when done right, especially in the hands of two perfectly matched screen legends", 3));
        movieList.add(new Movie(MovieType.BLOCKBUSTER, "Ticket To Paradise", MovieStatus.NOW_SHOWING,
        "A divorced couple teams up and travels to Bali to stop their daughter from making the same mistake they think they made 25 years ago..",
          "Ol Parker", cast9, MovieRating.PG13, reviews8, 8982));
        /* End of Movie 9 */

        /* Movie 10 */
        String[] cast10 = {"Sam Rockwell, Saoirse Ronan, Adrien Brody, Ruth Wilson, Reece Shearsmith, Harris Dickinson, David Oyelowo"};
        List<Review> reviews9 = new ArrayList<>();
        reviews9.add(new Review("Saul", "It may not be the smartest or funniest whodunit out there but See How They Run is still a really fun murder mystery that succeeds at creating an engaging mystery and solid gag rate bolstered by some surprisingly dark and mature themes", 4));
        reviews9.add(new Review("Steph", "I found this a really novel and almost throwback film with loads of great laughs and twists but never takes itself too seriously", 3));
        reviews9.add(new Review("Gerard", "This is one of those films that people have rubbished purely because it's primary purpose is to entertain. And that's a great shame, because in these troubled times that's perhaps exactly what people want. A good night out at the cinema", 4));
        movieList.add(new Movie(MovieType.IMAX, "See How They Run", MovieStatus.NOW_SHOWING,
        "In the West End of 1950s London, plans for a movie version of a smash-hit play come to an abrupt halt after a pivotal member of the crew is murdered",
          "Tom George", cast10, MovieRating.PG13, reviews9, 12122));
        /* End of Movie 10 */


        for(int i = 0; i < movieList.size(); i++) {
            movieList.get(i).setOverallRating();
        }

        FileAccessController.writeSerializedObject(MovieFileAccess.fileName, movieList);
    }
}
