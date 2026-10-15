export interface Article {
  id: string;
  section: string;
  categoryTag?: string;
  categoryTagColor?: string;
  isAnalysis?: boolean;
  isLive?: boolean;
  title: string;
  subtitle: string;
  author: string;
  authorTitle?: string;
  readTime: string;
  date: string;
  imageUrl?: string;
  imageCaption?: string;
  imageCredit?: string;
  body: string[];
  bullets?: {
    tag?: string;
    text: string;
    linkedArticleId?: string;
  }[];
  relatedArticles?: string[];
  commentsCount?: number;
}

export interface OpinionPiece {
  id: string;
  author: string;
  authorRole: string;
  title: string;
  readTime: string;
  date: string;
  body: string[];
}

export interface PollingData {
  state: string;
  race: string;
  leaderMargin: string;
  democrat: { candidate: string; percentage: number };
  republican: { candidate: string; percentage: number };
  undecided: number;
}

export type ActiveView = 
  | { type: 'home' }
  | { type: 'article'; articleId: string }
  | { type: 'section'; sectionName: string };
