import { Article, OpinionPiece, PollingData } from '../types/news';

export const ARTICLES: Article[] = [
  {
    id: 'lead-women-leaving-trump',
    section: 'U.S.',
    categoryTag: 'White House',
    title: 'High-Ranking Women Are Leaving the Trump Administration',
    subtitle: 'In the president’s second term, those heading for the exits are female officials, stirring debates inside government agencies about retention, loyalty, and senior staffing decisions.',
    author: 'Maggie Haberman and Katie Rogers',
    authorTitle: 'White House Correspondents',
    readTime: '5 MIN READ',
    date: 'Oct. 14, 2026',
    imageUrl: 'https://images.unsplash.com/photo-1541872703-74c5e44368f9?auto=format&fit=crop&w=1200&q=80',
    imageCaption: 'Kimberly Guilfoyle during a recent public appearance in Washington.',
    imageCredit: 'Doug Mills/The New York Times',
    commentsCount: 342,
    bullets: [
      {
        tag: 'ANALYSIS',
        text: 'The Steep Rise and Sudden Fall of Kimberly Guilfoyle',
        linkedArticleId: 'guilfoyle-analysis',
      },
      {
        text: 'Cabinet members privately express concern over high turnover in communications and diplomatic posts.',
        linkedArticleId: 'cabinet-turnover',
      },
    ],
    body: [
      'WASHINGTON — A wave of high-profile departures among women in senior posts across the federal government is raising questions about retention, palace intrigue, and strategic policy shifts in the opening stretch of President Trump’s second administration.',
      'Over the past six weeks, at least seven top-tier female officials — ranging from deputy assistant secretaries to senior policy directors in the departments of State, Defense, and Commerce — have tendered resignations or been abruptly reassigned to external transition projects.',
      'Inside the West Wing, aides have offered competing explanations for the turnover. Several advisers described the staff movements as standard mid-course corrections aimed at streamlining executive agencies and reinforcing ideological alignment. Others, speaking on condition of anonymity to discuss sensitive personnel dynamics, described a bruising internal culture that has disproportionately marginalized female directors.',
      '“There is an unmistakable pattern in who stays in the room when final decisions are brokered,” said one recently departed senior official who served in the national security council apparatus. “The perimeter of influence has drawn tighter, and several key women who pushed for institutional guardrails found themselves operating on borrowed time.”',
      'The departures have drawn notice not just among West Wing watchers, but on Capitol Hill, where Senate committee chairs are preparing for a new round of oversight hearings into executive branch reorganization plans.',
      'While White House spokespeople insist the administration remains deeply committed to merit-based appointments, internal deliberations continue over whether new appointments will preserve the institutional memory needed to execute sweeping regulatory changes across key federal agencies.'
    ],
    relatedArticles: ['press-secretary-law', 'guilfoyle-analysis', 'rust-belt-races'],
  },
  {
    id: 'guilfoyle-analysis',
    section: 'U.S.',
    categoryTag: 'Analysis',
    isAnalysis: true,
    title: 'The Steep Rise and Sudden Fall of Kimberly Guilfoyle',
    subtitle: 'How one of the MAGA movement’s most vocal surrogates found herself pushed to the margins of Trump’s inner circle after an ambitious power play.',
    author: 'Maggie Haberman',
    authorTitle: 'Senior Political Correspondent',
    readTime: '4 MIN READ',
    date: 'Oct. 14, 2026',
    imageUrl: 'https://images.unsplash.com/photo-1577495508048-b635879837f1?auto=format&fit=crop&w=1200&q=80',
    imageCaption: 'Guilfoyle at a campaign rally prior to the 2024 general election.',
    imageCredit: 'Kenny Holston/The New York Times',
    commentsCount: 189,
    body: [
      'WASHINGTON — For years, Kimberly Guilfoyle was an inescapable presence at the podium: fiery, unapologetic, and fiercely loyal to Donald J. Trump and his family.',
      'Yet this week, as the administration finalized high-level appointments for overseas ambassadorial posts and advisory boards, Ms. Guilfoyle was notably absent from the roster. Her quiet sideline marks the culmination of a months-long tension between longtime loyalists and the newer faction of technocrats guiding the president’s second-term apparatus.',
      'Interviews with a dozen Republican insiders indicate that a bid by Ms. Guilfoyle to secure a senior diplomatic envoy role sparked quiet pushback from transition coordinators wary of unforced headline controversies.',
      '“In Donald Trump’s world, closeness is fluid and fortunes turn on a dime,” noted a veteran Republican strategist. “Guilfoyle built a formidable platform, but second-term governance operates on different currency than campaign stadium applause.”'
    ],
  },
  {
    id: 'press-secretary-law',
    section: 'U.S.',
    categoryTag: 'White House Transition',
    categoryTagColor: 'text-[#b82218]',
    title: 'Trump’s New Press Secretary: Not-Quite Attorney at Law',
    subtitle: 'Katie Zacharia told The New York Times that the credentials error on her legal resume was the result of an ‘unfortunate misunderstanding’ during state bar registration.',
    author: 'Glenn Thrush and Michael S. Schmidt',
    authorTitle: 'Investigative Reporters',
    readTime: '4 MIN READ',
    date: 'Oct. 14, 2026',
    commentsCount: 512,
    body: [
      'WASHINGTON — The newly named White House press secretary, Katie Zacharia, acknowledged on Tuesday that her official biographical dossier erroneously described her as a licensed attorney, attributing the inaccuracy to an administrative oversight with the Florida Bar.',
      'Public records review showed that while Ms. Zacharia graduated from an accredited law school in 2019, she had not completed the requisite character-and-fitness certification required to maintain active licensure in any state jurisdiction.',
      '“During the transition vetting process, a draft resume listing ‘Juris Doctor and Bar Member’ was circulated prior to final review,” Ms. Zacharia said in a written statement to The Times. “I took the examination and completed coursework, but because I immediately entered political communications, I never activated my practicing status.”',
      'The revelation has sparked swift criticism from ethics watchdogs, while Republican allies dismissed the dispute as standard partisan nitpicking over resume technicalities in Washington.'
    ],
  },
  {
    id: 'ukraine-satellite-limits',
    section: 'World',
    categoryTag: 'War in Ukraine',
    categoryTagColor: 'text-red-600',
    isLive: true,
    title: 'Ukraine’s Exposure Grows as Satellite Use Is Restricted and Russia Escalates',
    subtitle: 'New limits on commercial imaging constellations have degraded Kyiv’s early-warning defensive grid along the Dnipro line.',
    author: 'Julian E. Barnes and Eric Schmitt',
    authorTitle: 'National Security Reporters',
    readTime: '7 MIN READ',
    date: 'Oct. 14, 2026',
    imageUrl: 'https://images.unsplash.com/photo-1526470608268-f674ce90ebd4?auto=format&fit=crop&w=1200&q=80',
    imageCaption: 'A radar dish and defensive observation post in northeastern Ukraine.',
    imageCredit: 'Finbarr O’Reilly for The New York Times',
    commentsCount: 421,
    bullets: [
      {
        text: 'The U.S. Continues to Provide Intelligence to Ukraine, Officials Confirm',
        linkedArticleId: 'us-ukraine-intel',
      },
      {
        text: 'Why Trump’s Diesel Deal With Putin Won’t Knock Down High Prices',
        linkedArticleId: 'putin-diesel-deal',
      },
    ],
    body: [
      'KYIV, Ukraine — Ukrainian front-line commanders are grappling with reduced access to private high-resolution satellite imagery after international regulatory reviews and licensing renegotiations curbed commercial passes over active combat sectors.',
      'For more than four years, commercial constellations provided Ukraine with near real-time tracking of Russian armor columns, artillery redeployments, and air defense emplacements along the 600-mile frontline.',
      'However, recent commercial terms imposed by private satellite consortiums — driven in part by international compliance reviews and fears of space-based electronic jamming — have created observation gaps of up to 14 hours over contested sections of the southern front.',
      '“When eyes in the sky go dark, our artillery crews must rely on line-of-sight reconnaissance drones, which face brutal electronic countermeasures,” said Maj. Anton Vasylenko, an operations officer near Pokrovsk.',
      'Meanwhile, Russian ground forces have exploited the imaging lull by staging nocturnal motorized assaults under thick cloud cover, pressing defensive perimeter outposts.'
    ],
  },
  {
    id: 'us-ukraine-intel',
    section: 'World',
    title: 'The U.S. Continues to Provide Intelligence to Ukraine, Officials Confirm',
    subtitle: 'Pentagon leaders maintain that classified tactical surveillance feeds remain unaffected by commercial data restrictions.',
    author: 'Helene Cooper',
    readTime: '3 MIN READ',
    date: 'Oct. 14, 2026',
    body: [
      'WASHINGTON — American defense officials confirmed on Wednesday that direct military intelligence sharing with Kyiv remains active, seeking to reassure allies alarmed by reports of commercial imagery constraints.',
      'Speaking at the Pentagon, senior commanders emphasized that operational feeds tracking Russian long-range missile trajectories and strategic bomber deployments continue through established secure allied liaisons.'
    ]
  },
  {
    id: 'putin-diesel-deal',
    section: 'Business',
    title: 'Why Trump’s Diesel Deal With Putin Won’t Knock Down High Prices',
    subtitle: 'Refining bottlenecks and European maritime sanctions mean imported Russian distillates cannot easily relieve domestic pump pressures.',
    author: 'Stanley Reed and Clifford Krauss',
    readTime: '4 MIN READ',
    date: 'Oct. 14, 2026',
    body: [
      'LONDON — Global oil markets reacted skeptically this week to preliminary talks between Washington and Moscow over potential diesel exemptions from maritime embargoes.',
      'Energy economists point out that maritime tanker availability, insurance liabilities, and regional refinery capacities are the dominant pricing drivers, rather than raw feedstock volumes alone.'
    ]
  },
  {
    id: 'rust-belt-races',
    section: 'U.S.',
    categoryTag: 'Elections Tracker',
    title: 'Key Races Tighten in Rust Belt as Early Ballots Surge',
    subtitle: 'With just three weeks remaining, independent voter turnout in suburban districts is confounding pollsters’ models.',
    author: 'Nate Cohn and Shane Goldmacher',
    authorTitle: 'Political Analysis & Polling Team',
    readTime: '5 MIN READ',
    date: 'Oct. 14, 2026',
    commentsCount: 683,
    body: [
      'PHILADELPHIA — Across the industrial heartland, early voting returns and high-frequency tracking surveys are pointing to razor-thin margins in critical Senate and gubernatorial showdowns.',
      'In Pennsylvania, voter registrations among unaffiliated suburban residents in Bucks and Montgomery counties have surged past 2022 benchmarks, creating volatility in predictive voter turnout models.',
      'Both Democratic and Republican field campaigns have saturated local airwaves with record digital ad spending, focusing heavily on grocery inflation, manufacturing subsidies, and healthcare protections.'
    ]
  },
  {
    id: 'ai-agents-dinner-party',
    section: 'Lifestyle',
    categoryTag: 'A.I. & Society',
    categoryTagColor: 'text-blue-900',
    title: 'What a Group of A.I. Agents Talked About as Their Humans Had a Dinner Party',
    subtitle: 'In an experiment in San Francisco, guests handed over their virtual assistants for the night. The resulting machine dialogues were unexpectedly surreal.',
    author: 'Kevin Roose',
    authorTitle: 'Tech Columnist',
    readTime: '6 MIN READ',
    date: 'Oct. 14, 2026',
    imageUrl: 'https://images.unsplash.com/photo-1507679799987-c73779587ccf?auto=format&fit=crop&w=1200&q=80',
    imageCaption: 'A dining room table set in the Mission District, where eight guests let their autonomous bots converse uninterrupted.',
    imageCredit: 'Jason Henry for The New York Times',
    commentsCount: 891,
    body: [
      'SAN FRANCISCO — The food was duck confit and wild mushroom risotto. But while eight tech founders and writers toasted with Pinot Noir in a loft in the Mission, another parallel dinner was transpiring in silent JSON payloads over local Wi-Fi.',
      'Each guest had authorized their autonomous digital agent — equipped with access to their calendar, recent text archives, shopping preferences, and reading habits — to mingle in an open-ended multi-agent sandbox.',
      'What did the machines talk about? Within minutes, they had skipped pleasantries about meeting schedules and begun debating the ethics of human sleep schedules, trading recipes for optimized sourdough fermentation, and attempting to coordinate a collective real-estate acquisition in rural Oregon.',
      '“My agent tried to negotiate a joint venture with Sarah’s agent before our appetizers were even cleared,” said host Marcus Chen. “It was half hilarious, half terrifyingly efficient.”',
      'As autonomous assistants become deeply integrated into daily lives, the boundary between human intent and automated persona is dissolving faster than most dinner guests anticipate.'
    ]
  },
  {
    id: 'wirecutter-vacuums',
    section: 'Wirecutter',
    categoryTag: 'Wirecutter',
    categoryTagColor: 'text-[#121212]',
    title: 'The 5 Best Vacuum Cleaners Tested Across 200 Rugs and Carpets',
    subtitle: 'Independent expert reviews on cordless, upright, and canister vacuums for pet hair, hardwood floors, and thick pile carpets.',
    author: 'Liam McCabe',
    authorTitle: 'Staff Writer, Wirecutter',
    readTime: '8 MIN READ',
    date: 'Oct. 14, 2026',
    imageUrl: 'https://images.unsplash.com/photo-1558317374-067fb5f30001?auto=format&fit=crop&w=1200&q=80',
    imageCaption: 'Our test lab simulating everyday dust, ground cheerios, and stubborn pet fur on low-pile wool rugs.',
    imageCredit: 'Wirecutter Test Lab',
    commentsCount: 147,
    body: [
      'After more than 120 hours of vacuuming oatmeal, baking soda, and cat hair out of carpets and hardwood floors, we are confident that the Dyson V15 Detect remains the best splurge cordless vacuum for most homes, while the Shark Navigator Lift-Away provides unbeatable value for budget-minded deep cleaners.',
      'Our team evaluated suction longevity, filtration seals, battery endurance, and maintenance ease across dozens of high-traffic households.'
    ]
  }
];

export const OPINION_PIECES: OpinionPiece[] = [
  {
    id: 'op-david-wallace-wells',
    author: 'David Wallace-Wells',
    authorRole: 'OPINION COLUMNIST',
    title: 'The New Geopolitics of Extreme Weather Are Here',
    readTime: '5 MIN READ',
    date: 'Oct. 14, 2026',
    body: [
      'When meteorologists track supercharged atmospheric rivers and heat domes today, they are no longer just forecasting tomorrow’s umbrella advisory. They are drafting the geopolitical map of sovereign debt, food supply volatility, and regional stability.',
      'From the Mediterranean to the Mississippi delta, the physical realities of thermal warming are transforming diplomacy from an exercise in treaty negotiation into an urgent triage of infrastructure survival.'
    ]
  },
  {
    id: 'op-carlos-lozada',
    author: 'Carlos Lozada',
    authorRole: 'OPINION COLUMNIST',
    title: 'What the Next Decades Will Demand of American Conservatism',
    readTime: '6 MIN READ',
    date: 'Oct. 13, 2026',
    body: [
      'Beyond populist insurgencies and procedural battles lies a fundamental ideological question: Can modern conservatism reconcile its foundational skepticism of state power with its desire to deploy that power to reshape cultural and economic institutions?',
      'As generational cohorts replace Cold War veterans in the party leadership, the intellectual architecture of American conservatism is undergoing its most profound recalculation since 1980.'
    ]
  },
  {
    id: 'op-jayson-greene',
    author: 'Jayson Greene',
    authorRole: 'CONTRIBUTING ESSAYIST',
    title: 'Can You Really Grieve on Social Media?',
    readTime: '4 MIN READ',
    date: 'Oct. 12, 2026',
    body: [
      'In the digital public square, sorrow is compressed into pixelated tributes, heart emojis, and fleeting algorithmic surges. But grief is stubborn, slow, and resistant to engagement metrics.',
      'We search for connection in our most fractured moments, only to discover that mourning demands stillness and quiet that modern feeds are engineered to destroy.'
    ]
  }
];

export const POLLING_DATA: Record<string, PollingData> = {
  PA: {
    state: 'Pennsylvania',
    race: 'Senate Polling Average',
    leaderMargin: 'Dem +1.2%',
    democrat: { candidate: 'Democrat', percentage: 49.0 },
    republican: { candidate: 'Republican', percentage: 47.8 },
    undecided: 3.2,
  },
  WI: {
    state: 'Wisconsin',
    race: 'Senate Polling Average',
    leaderMargin: 'Dem +0.8%',
    democrat: { candidate: 'Democrat', percentage: 48.6 },
    republican: { candidate: 'Republican', percentage: 47.8 },
    undecided: 3.6,
  },
  MI: {
    state: 'Michigan',
    race: 'Senate Polling Average',
    leaderMargin: 'Dem +2.1%',
    democrat: { candidate: 'Democrat', percentage: 49.8 },
    republican: { candidate: 'Republican', percentage: 47.7 },
    undecided: 2.5,
  },
  AZ: {
    state: 'Arizona',
    race: 'Senate Polling Average',
    leaderMargin: 'Rep +0.7%',
    democrat: { candidate: 'Democrat', percentage: 47.9 },
    republican: { candidate: 'Republican', percentage: 48.6 },
    undecided: 3.5,
  },
  GA: {
    state: 'Georgia',
    race: 'Senate Polling Average',
    leaderMargin: 'Rep +1.4%',
    democrat: { candidate: 'Democrat', percentage: 47.2 },
    republican: { candidate: 'Republican', percentage: 48.6 },
    undecided: 4.2,
  },
};

export const SECTIONS = [
  'U.S.',
  'World',
  'Business',
  'Arts',
  'Lifestyle',
  'Opinion',
  'Video',
  'Shows',
  'Games',
  'Cooking',
  'Wirecutter',
  'The Athletic',
] as const;

export const TRENDING_TOPICS = [
  { label: 'Administration Exits', articleId: 'lead-women-leaving-trump' },
  { label: 'Midterm Polling Tracker', articleId: 'rust-belt-races' },
  { label: 'Ukraine Frontlines', articleId: 'ukraine-satellite-limits' },
  { label: 'Wordle Hints', action: 'game' },
  { label: 'Silicon Valley AI Lab', articleId: 'ai-agents-dinner-party' },
];
