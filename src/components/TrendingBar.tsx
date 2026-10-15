import React from 'react';
import { TRENDING_TOPICS } from '../data/newsData';

interface TrendingBarProps {
  onSelectTopic: (topic: typeof TRENDING_TOPICS[number]) => void;
}

export const TrendingBar: React.FC<TrendingBarProps> = ({ onSelectTopic }) => {
  return (
    <div className="text-[11px] text-[#666] mb-3 flex items-center gap-2 border-b border-nyt pb-2 overflow-x-auto no-scrollbar">
      <span className="font-bold text-[#121212] uppercase tracking-wider text-[10px] shrink-0">
        Trending
      </span>
      {TRENDING_TOPICS.map((topic, index) => (
        <React.Fragment key={topic.label}>
          <span className="text-gray-300 select-none">•</span>
          <button
            onClick={() => onSelectTopic(topic)}
            className="hover:underline text-[#333] hover:text-black font-medium whitespace-nowrap cursor-pointer transition-colors"
          >
            {topic.label}
          </button>
        </React.Fragment>
      ))}
    </div>
  );
};
