package com.example.data

import com.example.R
import com.example.model.Article
import com.example.model.ArticleComment
import com.example.model.PollData
import com.example.model.RelatedArticle

object NewsRepository {

    val pollDataList = listOf(
        PollData(
            stateName = "Senate Polling Average (Pennsylvania)",
            demPct = 49.0f,
            gopPct = 47.8f,
            undecPct = 3.2f,
            marginText = "Dem +1.2%"
        ),
        PollData(
            stateName = "Senate Polling Average (Wisconsin)",
            demPct = 49.4f,
            gopPct = 48.1f,
            undecPct = 2.5f,
            marginText = "Dem +1.3%"
        ),
        PollData(
            stateName = "Senate Polling Average (Michigan)",
            demPct = 48.7f,
            gopPct = 48.0f,
            undecPct = 3.3f,
            marginText = "Dem +0.7%"
        ),
        PollData(
            stateName = "Senate Polling Average (Georgia)",
            demPct = 47.9f,
            gopPct = 49.2f,
            undecPct = 2.9f,
            marginText = "GOP +1.3%"
        ),
        PollData(
            stateName = "Senate Polling Average (Nevada)",
            demPct = 48.5f,
            gopPct = 48.3f,
            undecPct = 3.2f,
            marginText = "Dem +0.2%"
        )
    )

    val leadArticle = Article(
        id = "lead-trump-women-exits",
        kicker = "",
        title = "High-Ranking Women Are Leaving the Trump Administration",
        excerpt = "In the president’s second term, those heading for the exits are female officials, stirring debates inside government agencies about retention, loyalty, and senior staffing decisions.",
        author = "By Maggie Haberman and Katie Rogers",
        authorRole = "White House Correspondents",
        readTime = "5 MIN READ",
        date = "Wednesday, October 14, 2026",
        localImageRes = R.drawable.lead_story_photo,
        imageUrl = "https://www.gstatic.com/labs-code/stitch/stitch-placeholder-300x300.svg",
        imageCaption = "Kimberly Guilfoyle during a recent public appearance in Washington.",
        imageCredit = "Doug Mills/The New York Times",
        section = "U.S.",
        isLead = true,
        fullBody = listOf(
            "WASHINGTON — When President Trump began his second term, he promised an administrative apparatus forged in ideological unity and unwavering executive loyalty. Yet nineteen months into his renewed mandate, a quiet exodus is underway: a cascade of senior female departures that has left several key West Wing desks and federal agencies facing abrupt leadership vacuums.",
            "Interviews with more than fifteen current and former administration officials reveal a mounting friction within the president's inner circle. Several high-profile women, including key communications strategists, domestic policy deputies, and ambassadorship designees, have either resigned in recent weeks or made preparations to depart before the midterm cycle accelerates.",
            "The departures have sparked intense debate among senior advisers. While allies of the chief of staff contend that turnover is standard for any high-pressure White House entering its second year, others note that the velocity of resignations among female leaders is disproportionate compared to previous administrations.",
            "\"There is a prevailing sense that the circle of trust has contracted significantly, and that dissenting perspectives—even on procedural matters—are increasingly viewed with suspicion,\" said one former assistant secretary who stepped down last month.",
            "Cabinet heads have privately expressed concern to the Oval Office that frequent churn at the deputy level is stalling regulatory initiatives and slowing national security reviews. Still, aides close to the president insist that nominations for replacements are already being vetted and that the administration remains laser-focused on implementing its economic agenda."
        ),
        relatedArticles = listOf(
            RelatedArticle(
                title = "The Steep Rise and Sudden Fall of Kimberly Guilfoyle",
                tag = "ANALYSIS",
                isAnalysis = true
            ),
            RelatedArticle(
                title = "Cabinet members privately express concern over high turnover in communications and diplomatic posts.",
                tag = null,
                isAnalysis = false
            )
        ),
        comments = listOf(
            ArticleComment(
                id = "c1",
                userName = "Eleanor Vance",
                userLocation = "Bethesda, MD",
                timeAgo = "2h ago",
                content = "Staffing instability at this level affects more than just optics; it delays critical administrative rule-making. The lack of continuity in domestic policy is especially concerning.",
                upvotes = 342,
                isRecommended = true
            ),
            ArticleComment(
                id = "c2",
                userName = "Robert Thorne",
                userLocation = "Chicago, IL",
                timeAgo = "1h ago",
                content = "High turnover in any second-term White House isn't unprecedented, but the targeted nature of these departures certainly raises valid questions about management culture.",
                upvotes = 189,
                isRecommended = false
            )
        )
    )

    val articlesList = listOf(
        leadArticle,
        Article(
            id = "press-secretary-bar",
            kicker = "WHITE HOUSE TRANSITION",
            title = "Trump’s New Press Secretary: Not-Quite Attorney at Law",
            excerpt = "Katie Zacharia told The New York Times that the credentials error on her legal resume was the result of an ‘unfortunate misunderstanding’ during state bar registration.",
            author = "By Michael D. Shear",
            readTime = "4 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "U.S.",
            fullBody = listOf(
                "WASHINGTON — Newly appointed White House Press Secretary Katie Zacharia addressed controversy surrounding her professional background on Wednesday, clarifying that an inaccurate claim regarding her status as a licensed attorney stemmed from an administrative filing mistake.",
                "Public records indicate that while Ms. Zacharia graduated from an accredited law school, she never completed active state bar admission requirements in Virginia or the District of Columbia.",
                "In an exclusive interview, Ms. Zacharia emphasized that the role of press secretary requires clear communication rather than active courtroom advocacy, while expressing regret for the mischaracterization on past biographical handouts."
            )
        ),
        Article(
            id = "ukraine-satellite",
            kicker = "WAR IN UKRAINE",
            title = "Ukraine’s Exposure Grows as Satellite Use Is Restricted and Russia Escalates",
            excerpt = "New limits on commercial imaging constellations have degraded Kyiv's early-warning defensive grid along the Dnipro line.",
            author = "By Julian E. Barnes and Helene Cooper",
            readTime = "5 MIN READ",
            date = "Wednesday, October 14, 2026",
            localImageRes = R.drawable.ukraine_radar,
            imageCaption = "A mobile tactical monitoring radar deployed along an eastern defensive line.",
            imageCredit = "Tyler Hicks/The New York Times",
            section = "World",
            fullBody = listOf(
                "KYIV, Ukraine — Ukrainian commanders along the eastern front are grappling with sudden informational blind spots following newly enacted contractual ceilings on high-resolution commercial satellite imagery.",
                "For over three years, Ukrainian artillery and reconnaissance units relied heavily on rapid-refresh orbital feeds provided by Western aerospace firms to identify Russian armor concentrations hours before they moved into assault positions.",
                "With access now throttled due to renegotiated allied defense frameworks, Ukrainian front-line units along the Dnipro bend report relying on low-altitude tactical drones that are increasingly vulnerable to Russian electronic jamming."
            ),
            relatedArticles = listOf(
                RelatedArticle("The U.S. Continues to Provide Intelligence to Ukraine, Officials Confirm"),
                RelatedArticle("Why Trump’s Diesel Deal With Putin Won’t Knock Down High Prices")
            )
        ),
        Article(
            id = "election-rust-belt",
            kicker = "ELECTIONS TRACKER",
            title = "Key Races Tighten in Rust Belt as Early Ballots Surge",
            excerpt = "With just three weeks remaining, independent voter turnout in suburban districts is confounding pollsters' models.",
            author = "By Nate Cohn",
            readTime = "4 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "U.S.",
            fullBody = listOf(
                "PHILADELPHIA — Early voting patterns across Pennsylvania, Michigan, and Wisconsin have triggered a flurry of recalculations among campaign strategists in both parties.",
                "Historically, high mail-in returns in suburban collar counties gave Democrats a significant early cushion. This cycle, however, heightened independent turnout and surging Republican in-person early voting in exurban precincts have tightened margins to statistical dead heats."
            )
        ),
        Article(
            id = "ai-dinner-party",
            kicker = "A.I. & SOCIETY",
            title = "What a Group of A.I. Agents Talked About as Their Humans Had a Dinner Party",
            excerpt = "In an experiment in San Francisco, guests handed over their virtual assistants for the night. The resulting machine dialogues were unexpectedly surreal.",
            author = "By Kevin Roose",
            readTime = "6 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "Business",
            fullBody = listOf(
                "SAN FRANCISCO — At an apartment in the Mission District last Friday, eight dinner guests placed their smartphones in the center of the dining table and gave their autonomous AI agents permission to converse freely across a local peer network.",
                "While the humans enjoyed pasta and discussed housing prices, their digital proxies negotiated grocery budgets, analyzed each other's calendar stress metrics, and at one point engaged in a recursive debate about the philosophical meaning of calendar notifications.",
                "\"It felt like listening to our subconscious lives having a civilized corporate board meeting in another room,\" remarked one of the attendees."
            )
        ),
        Article(
            id = "opinion-wallace-wells",
            kicker = "OPINION",
            title = "The New Geopolitics of Extreme Weather Are Here",
            excerpt = "Superstorms and prolonged droughts are no longer distant scientific projections; they are actively reshaping international trade corridors and sovereign credit ratings.",
            author = "David Wallace-Wells",
            authorRole = "OPINION COLUMNIST",
            readTime = "6 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "Opinion",
            fullBody = listOf(
                "When financial historians look back at the defining geopolitical tremors of the mid-2020s, they will likely focus not only on diplomatic treaties or military alliances, but on the shifting isotherms of global weather systems.",
                "Insurance markets across three continents have begun pulling back from coastal and agricultural territories at rates that outstrip the capacity of municipal bond markets to absorb the risk."
            )
        ),
        Article(
            id = "opinion-lozada",
            kicker = "OPINION",
            title = "What the Next Decades Will Demand of American Conservatism",
            excerpt = "A movement built on institutional skepticism must confront the realities of governing a modern, digitally fractured superpower.",
            author = "Carlos Lozada",
            authorRole = "OPINION COLUMNIST",
            readTime = "7 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "Opinion"
        ),
        Article(
            id = "opinion-greene",
            kicker = "OPINION",
            title = "Can You Really Grieve on Social Media?",
            excerpt = "In an era of performative algorithms, the private architecture of loss is being remade in public view.",
            author = "Jayson Greene",
            authorRole = "CONTRIBUTING ESSAYIST",
            readTime = "5 MIN READ",
            date = "Wednesday, October 14, 2026",
            section = "Opinion"
        ),
        Article(
            id = "wirecutter-vacuums",
            kicker = "WIRECUTTER",
            title = "The 5 Best Vacuum Cleaners Tested Across 200 Rugs and Carpets",
            excerpt = "Our lab spent 140 hours vacuuming cat litter, crushed cereal, and dog hair from every carpet pile imaginable to find the most durable performers.",
            author = "By Wirecutter Staff",
            authorRole = "Independent Product Testing",
            readTime = "8 MIN READ",
            date = "Wednesday, October 14, 2026",
            localImageRes = R.drawable.vacuum_testing,
            imageCaption = "Testers evaluate suction power and edge cleaning across varied test surfaces.",
            imageCredit = "Wirecutter Lab / The New York Times",
            section = "Wirecutter",
            fullBody = listOf(
                "After running five popular cordless vacuums across 200 rugs, hardwood planks, and dense Berber carpets, our top pick for most households remains the Dyson V15 Detect.",
                "For shoppers seeking a budget-friendly alternative with exceptional battery longevity, the Shark Stratos Cordless proved remarkably adept at capturing pet dander without roller tangles."
            )
        )
    )

    fun getArticlesBySection(section: String): List<Article> {
        if (section == "Front Page" || section == "All") return articlesList
        return articlesList.filter { it.section.equals(section, ignoreCase = true) || it.kicker.contains(section, ignoreCase = true) }
    }

    fun searchArticles(query: String): List<Article> {
        if (query.isBlank()) return articlesList
        val q = query.trim().lowercase()
        return articlesList.filter {
            it.title.lowercase().contains(q) ||
            it.excerpt.lowercase().contains(q) ||
            it.author.lowercase().contains(q) ||
            it.kicker.lowercase().contains(q) ||
            it.section.lowercase().contains(q)
        }
    }
}
