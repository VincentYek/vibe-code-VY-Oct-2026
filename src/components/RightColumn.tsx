import React from 'react';
import { OPINION_PIECES } from '../data/newsData';
import { Article } from '../types/news';

interface RightColumnProps {
  onSelectOpinion: (opinionId: string) => void;
  onOpenGame: (gameName: string) => void;
  onSelectArticle: (articleId: string) => void;
  wirecutterArticle: Article;
}

export const RightColumn: React.FC<RightColumnProps> = ({
  onSelectOpinion,
  onOpenGame,
  onSelectArticle,
  wirecutterArticle,
}) => {
  return (
    <section aria-labelledby="opinion-lifestyle-heading" className="lg:col-span-3 space-y-6">
      <h2 className="sr-only" id="opinion-lifestyle-heading">
        Opinion and Features
      </h2>

      {/* Opinion Columnists Block */}
      <div className="border-b border-nyt pb-4">
        <h4 className="text-[11px] font-bold tracking-widest uppercase border-b-2 border-black pb-1 mb-3">
          Opinion
        </h4>
        <div className="space-y-4 font-headline">
          {OPINION_PIECES.map((piece, index) => (
            <article
              key={piece.id}
              onClick={() => onSelectOpinion(piece.id)}
              className={`hover:bg-gray-50 p-1 -m-1 transition-colors cursor-pointer ${
                index > 0 ? 'border-t border-dotted border-gray-300 pt-3' : ''
              }`}
            >
              <span className="text-[11px] font-sans-editorial font-bold text-[#121212] block">
                {piece.author}
              </span>
              <h5 className="text-[15px] font-bold leading-tight hover:underline">
                {piece.title}
              </h5>
              <span className="text-[10px] font-sans-editorial text-gray-500">
                {piece.authorRole}
              </span>
            </article>
          ))}
        </div>
      </div>

      {/* NYT Games Showcase Box */}
      <div className="bg-[#f7f7f7] border border-nyt p-3.5 rounded-sm" data-purpose="nyt-games-card">
        <div className="flex items-center justify-between pb-2 border-b border-gray-300 mb-2">
          <span className="text-[11px] font-bold uppercase tracking-wider text-black">
            Play NYT Games
          </span>
          <span className="text-[10px] text-gray-500">Daily Puzzles</span>
        </div>
        <div className="grid grid-cols-2 gap-2 text-center text-xs">
          <button
            onClick={() => onOpenGame('Wordle')}
            className="p-2 bg-white rounded border border-gray-200 hover:border-black transition-colors cursor-pointer group text-left sm:text-center"
          >
            <div className="font-bold text-green-700 text-sm group-hover:scale-105 transition-transform">
              Wordle
            </div>
            <div className="text-[10px] text-gray-500">No. 1,211</div>
          </button>

          <button
            onClick={() => onOpenGame('Connections')}
            className="p-2 bg-white rounded border border-gray-200 hover:border-black transition-colors cursor-pointer group text-left sm:text-center"
          >
            <div className="font-bold text-amber-700 text-sm group-hover:scale-105 transition-transform">
              Connections
            </div>
            <div className="text-[10px] text-gray-500">Group by topic</div>
          </button>

          <button
            onClick={() => onOpenGame('Spelling Bee')}
            className="p-2 bg-white rounded border border-gray-200 hover:border-black transition-colors cursor-pointer group text-left sm:text-center"
          >
            <div className="font-bold text-yellow-600 text-sm group-hover:scale-105 transition-transform">
              Spelling Bee
            </div>
            <div className="text-[10px] text-gray-500">Genius ranking</div>
          </button>

          <button
            onClick={() => onOpenGame('The Mini')}
            className="p-2 bg-white rounded border border-gray-200 hover:border-black transition-colors cursor-pointer group text-left sm:text-center"
          >
            <div className="font-bold text-blue-700 text-sm group-hover:scale-105 transition-transform">
              The Mini
            </div>
            <div className="text-[10px] text-gray-500">Quick solve</div>
          </button>
        </div>
      </div>

      {/* Wirecutter Recommendations Box */}
      <div
        onClick={() => onSelectArticle(wirecutterArticle.id)}
        className="border border-nyt p-3.5 rounded-sm hover:border-black transition-colors cursor-pointer group"
        data-purpose="wirecutter-card"
      >
        <div className="flex items-center gap-1.5 mb-2">
          <span className="w-2.5 h-2.5 bg-orange-600 rounded-xs" />
          <h5 className="text-[11px] font-bold uppercase tracking-wider text-[#121212]">
            Wirecutter
          </h5>
        </div>
        <p className="font-headline text-sm font-semibold group-hover:underline cursor-pointer mb-1">
          {wirecutterArticle.title}
        </p>
        <span className="text-[10px] text-gray-500">Independent expert reviews</span>
      </div>
    </section>
  );
};
