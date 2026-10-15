import React, { useState } from 'react';
import { Article, PollingData } from '../types/news';
import { POLLING_DATA } from '../data/newsData';
import { ChevronDown, BarChart2 } from 'lucide-react';

interface CenterColumnProps {
  ukraineArticle: Article;
  electionsArticle: Article;
  aiArticle: Article;
  onSelectArticle: (articleId: string) => void;
  onOpenPollingModal: (stateCode: string) => void;
}

export const CenterColumn: React.FC<CenterColumnProps> = ({
  ukraineArticle,
  electionsArticle,
  aiArticle,
  onSelectArticle,
  onOpenPollingModal,
}) => {
  const [selectedState, setSelectedState] = useState<string>('PA');
  const poll = POLLING_DATA[selectedState] || POLLING_DATA.PA;

  return (
    <section
      aria-labelledby="world-domestic-heading"
      className="lg:col-span-4 lg:border-r border-nyt lg:pr-6"
    >
      <h2 className="sr-only" id="world-domestic-heading">
        World and Domestic News
      </h2>

      {/* Russia-Ukraine War Package */}
      <article className="mb-5 pb-5 border-b border-nyt">
        <div className="flex items-center gap-1.5 mb-1.5">
          <span className="inline-block w-2 h-2 rounded-full bg-red-600 animate-pulse" />
          <span className="text-[11px] font-bold uppercase tracking-wider text-red-600">
            {ukraineArticle.categoryTag || 'War in Ukraine'}
          </span>
        </div>
        <h3
          onClick={() => onSelectArticle(ukraineArticle.id)}
          className="font-headline text-xl font-bold leading-snug hover:text-[#555] cursor-pointer mb-2"
        >
          {ukraineArticle.title}
        </h3>
        <p
          onClick={() => onSelectArticle(ukraineArticle.id)}
          className="font-headline text-[14px] text-[#555] leading-relaxed mb-2 cursor-pointer"
        >
          {ukraineArticle.subtitle}
        </p>

        {ukraineArticle.bullets && ukraineArticle.bullets.length > 0 && (
          <div className="space-y-2 mt-3 pt-2 text-[13px] font-headline border-t border-dotted border-gray-300">
            {ukraineArticle.bullets.map((b, idx) => (
              <button
                key={idx}
                onClick={() => b.linkedArticleId && onSelectArticle(b.linkedArticleId)}
                className="block text-left hover:underline text-[#121212] font-medium cursor-pointer w-full"
              >
                • {b.text}
              </button>
            ))}
          </div>
        )}
      </article>

      {/* Midterm Polling & Senate Dispatch */}
      <article className="mb-5 pb-5 border-b border-nyt">
        <div className="flex items-center justify-between mb-1">
          <span className="text-[10px] uppercase font-bold text-gray-500 tracking-wider block">
            {electionsArticle.categoryTag || 'Elections Tracker'}
          </span>
          {/* Quick State Selector */}
          <div className="flex items-center gap-1 text-[10px] font-medium text-gray-500">
            <span>State:</span>
            {(['PA', 'WI', 'MI', 'AZ'] as const).map((st) => (
              <button
                key={st}
                onClick={() => setSelectedState(st)}
                className={`px-1.5 py-0.5 rounded text-[9px] uppercase font-bold transition-colors cursor-pointer ${
                  selectedState === st
                    ? 'bg-black text-white'
                    : 'text-gray-600 hover:bg-gray-100'
                }`}
              >
                {st}
              </button>
            ))}
          </div>
        </div>

        <h3
          onClick={() => onSelectArticle(electionsArticle.id)}
          className="font-headline text-lg font-bold leading-snug hover:underline cursor-pointer mb-1.5"
        >
          {electionsArticle.title}
        </h3>
        <p
          onClick={() => onSelectArticle(electionsArticle.id)}
          className="font-headline text-[13px] text-[#444] mb-3 cursor-pointer"
        >
          {electionsArticle.subtitle}
        </p>

        {/* Polling Mini-Bar Widget */}
        <div
          className="bg-gray-50 border border-nyt p-2.5 rounded-sm hover:border-gray-400 transition-colors group cursor-pointer"
          data-purpose="polling-widget"
          onClick={() => onOpenPollingModal(selectedState)}
          title="Click to explore interactive battleground Senate tracker"
        >
          <div className="text-[10px] font-bold uppercase tracking-wider text-gray-600 mb-1.5 flex justify-between items-center">
            <span className="flex items-center gap-1">
              <BarChart2 className="w-3 h-3 text-gray-500" />
              <span>
                Senate Polling Average ({poll.state})
              </span>
            </span>
            <span className={poll.leaderMargin.startsWith('Dem') ? 'text-blue-700' : 'text-red-700'}>
              {poll.leaderMargin}
            </span>
          </div>

          <div className="w-full bg-gray-200 h-2 rounded-full overflow-hidden flex shadow-inner">
            <div
              style={{ width: `${poll.democrat.percentage}%` }}
              className="bg-blue-600 h-full transition-all duration-300"
              title={`Democrat: ${poll.democrat.percentage}%`}
            />
            <div
              style={{ width: `${poll.republican.percentage}%` }}
              className="bg-red-500 h-full transition-all duration-300"
              title={`Republican: ${poll.republican.percentage}%`}
            />
            <div
              style={{ width: `${poll.undecided}%` }}
              className="bg-yellow-400 h-full transition-all duration-300"
              title={`Undecided: ${poll.undecided}%`}
            />
          </div>

          <div className="flex justify-between text-[9px] text-gray-500 mt-1.5 font-sans-editorial">
            <span>Democrat {poll.democrat.percentage.toFixed(1)}%</span>
            <span>Undecided {poll.undecided.toFixed(1)}%</span>
            <span>Republican {poll.republican.percentage.toFixed(1)}%</span>
          </div>
        </div>
      </article>

      {/* Tech & Artificial Intelligence Feature */}
      <article>
        <span className="text-[10px] uppercase font-bold text-blue-900 tracking-wider block mb-1">
          {aiArticle.categoryTag || 'A.I. & Society'}
        </span>
        <h3
          onClick={() => onSelectArticle(aiArticle.id)}
          className="font-headline text-lg font-bold leading-snug hover:underline cursor-pointer mb-1.5"
        >
          {aiArticle.title}
        </h3>
        <p
          onClick={() => onSelectArticle(aiArticle.id)}
          className="font-headline text-[13px] text-[#444] mb-2 cursor-pointer"
        >
          {aiArticle.subtitle}
        </p>
        <span className="text-[11px] text-[#727272]">{aiArticle.readTime}</span>
      </article>
    </section>
  );
};
