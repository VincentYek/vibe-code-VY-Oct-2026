import React from 'react';
import { Article, OpinionPiece } from '../types/news';
import { ARTICLES, OPINION_PIECES } from '../data/newsData';
import { ArrowLeft, Filter } from 'lucide-react';

interface SectionViewProps {
  sectionName: string;
  onSelectArticle: (articleId: string) => void;
  onBackToHome: () => void;
  onOpenGame?: (game: string) => void;
  useWireframePhotos: boolean;
}

export const SectionView: React.FC<SectionViewProps> = ({
  sectionName,
  onSelectArticle,
  onBackToHome,
  onOpenGame,
  useWireframePhotos,
}) => {
  const sectionArticles = ARTICLES.filter(
    (a) => a.section.toLowerCase() === sectionName.toLowerCase()
  );

  const fallbackArticles = sectionArticles.length > 0 ? sectionArticles : ARTICLES.slice(0, 4);

  return (
    <div className="w-full max-w-[1340px] mx-auto px-4 py-4">
      {/* Section Breadcrumb & Header */}
      <div className="flex items-center justify-between border-b-2 border-black pb-3 mb-6">
        <div className="flex items-center gap-3">
          <button
            onClick={onBackToHome}
            className="flex items-center gap-1.5 text-xs font-semibold text-gray-600 hover:text-black cursor-pointer group"
          >
            <ArrowLeft className="w-4 h-4 transition-transform group-hover:-translate-x-0.5" />
            <span>Front Page</span>
          </button>
          <span className="text-gray-300">/</span>
          <h2 className="font-headline text-3xl sm:text-4xl font-bold text-black capitalize">
            {sectionName}
          </h2>
        </div>
        <span className="text-xs text-gray-500 font-sans-editorial">
          {sectionArticles.length} Stories in {sectionName}
        </span>
      </div>

      {/* If Games Section */}
      {sectionName === 'Games' ? (
        <div className="grid grid-cols-1 md:grid-cols-4 gap-6 py-6">
          {[
            { name: 'Wordle', desc: 'Guess the 5-letter word in 6 tries.', color: 'text-green-700' },
            { name: 'Connections', desc: 'Group words by their hidden common threads.', color: 'text-amber-700' },
            { name: 'Spelling Bee', desc: 'Construct anagrams from the honeycomb letters.', color: 'text-yellow-600' },
            { name: 'The Mini', desc: 'Solve today’s compact 5x5 crossword puzzle.', color: 'text-blue-700' },
          ].map((game) => (
            <div
              key={game.name}
              onClick={() => onOpenGame && onOpenGame(game.name)}
              className="p-6 border border-nyt rounded-lg bg-gray-50 hover:border-black hover:shadow-md transition-all cursor-pointer group text-center"
            >
              <h3 className={`font-bold text-2xl mb-2 group-hover:scale-105 transition-transform ${game.color}`}>
                {game.name}
              </h3>
              <p className="text-xs text-gray-600 mb-4">{game.desc}</p>
              <button className="bg-black text-white text-xs font-bold px-4 py-2 rounded uppercase tracking-wider group-hover:bg-gray-800">
                Play Now
              </button>
            </div>
          ))}
        </div>
      ) : (
        /* Standard Section Feed */
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8">
          {/* Main Column */}
          <div className="lg:col-span-8 space-y-8">
            {fallbackArticles.map((art, index) => (
              <article
                key={art.id}
                onClick={() => onSelectArticle(art.id)}
                className={`group cursor-pointer ${
                  index > 0 ? 'pt-6 border-t border-nyt' : ''
                }`}
              >
                <div className="grid grid-cols-1 sm:grid-cols-12 gap-4">
                  <div className="sm:col-span-8">
                    {art.categoryTag && (
                      <span className="text-[10px] uppercase font-bold text-red-700 tracking-wider block mb-1">
                        {art.categoryTag}
                      </span>
                    )}
                    <h3 className="font-headline text-2xl font-bold text-black group-hover:text-gray-600 leading-snug mb-2">
                      {art.title}
                    </h3>
                    <p className="font-headline text-sm text-[#444] mb-3 line-clamp-3">
                      {art.subtitle}
                    </p>
                    <div className="text-[11px] text-gray-500 font-sans-editorial">
                      By {art.author} • {art.readTime}
                    </div>
                  </div>

                  {art.imageUrl && (
                    <div className="sm:col-span-4">
                      {useWireframePhotos ? (
                        <div className="w-full h-32 bg-[#ebe7f5] border border-gray-200 flex items-center justify-center">
                          <div className="w-8 h-8 text-[#7c6bb5]">
                            <svg className="w-full h-full" viewBox="0 0 48 48" fill="none" stroke="currentColor" strokeWidth="2.5">
                              <rect x="4" y="6" width="40" height="36" rx="4" />
                              <circle cx="16" cy="18" r="4" fill="currentColor" />
                              <path d="M4 36l12-14 10 12 8-8 10 10" />
                            </svg>
                          </div>
                        </div>
                      ) : (
                        <img
                          src={art.imageUrl}
                          alt={art.title}
                          className="w-full h-32 object-cover rounded-xs"
                        />
                      )}
                    </div>
                  )}
                </div>
              </article>
            ))}
          </div>

          {/* Sidebar */}
          <div className="lg:col-span-4 lg:border-l border-nyt lg:pl-6 space-y-6">
            <div className="border-b-2 border-black pb-1">
              <h4 className="font-sans-editorial text-xs font-bold uppercase tracking-wider text-black">
                Featured Opinion
              </h4>
            </div>
            {OPINION_PIECES.map((piece) => (
              <div key={piece.id} className="border-b border-nyt pb-3">
                <span className="text-xs font-bold text-black block">{piece.author}</span>
                <h5 className="font-headline text-sm font-semibold hover:underline cursor-pointer">
                  {piece.title}
                </h5>
                <span className="text-[10px] text-gray-500">{piece.authorRole}</span>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};
