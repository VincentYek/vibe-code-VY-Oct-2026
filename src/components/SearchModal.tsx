import React, { useState } from 'react';
import { Search, X, ArrowRight } from 'lucide-react';
import { ARTICLES, OPINION_PIECES } from '../data/newsData';

interface SearchModalProps {
  onClose: () => void;
  onSelectArticle: (articleId: string) => void;
}

export const SearchModal: React.FC<SearchModalProps> = ({
  onClose,
  onSelectArticle,
}) => {
  const [query, setQuery] = useState('');

  const filteredArticles = ARTICLES.filter(
    (a) =>
      a.title.toLowerCase().includes(query.toLowerCase()) ||
      a.subtitle.toLowerCase().includes(query.toLowerCase()) ||
      a.author.toLowerCase().includes(query.toLowerCase()) ||
      a.section.toLowerCase().includes(query.toLowerCase())
  );

  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-start justify-center p-4 pt-16">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-xl overflow-hidden border border-gray-300">
        {/* Search Bar Input */}
        <div className="p-3 border-b border-nyt flex items-center gap-3 bg-gray-50">
          <Search className="w-5 h-5 text-gray-500" />
          <input
            type="text"
            autoFocus
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Search news, topics, opinion columnists..."
            className="w-full bg-transparent border-none text-base font-sans-editorial text-black focus:outline-none placeholder:text-gray-400"
          />
          {query && (
            <button
              onClick={() => setQuery('')}
              className="p-1 text-gray-400 hover:text-black cursor-pointer"
            >
              <X className="w-4 h-4" />
            </button>
          )}
          <button
            onClick={onClose}
            className="text-xs font-semibold px-2 py-1 text-gray-500 hover:text-black cursor-pointer"
          >
            Esc
          </button>
        </div>

        {/* Results */}
        <div className="max-h-[60vh] overflow-y-auto p-4">
          <div className="text-[10px] font-bold uppercase tracking-wider text-gray-500 mb-2">
            {query ? `Results for "${query}" (${filteredArticles.length})` : 'Popular in The Times'}
          </div>

          <div className="space-y-2">
            {filteredArticles.length > 0 ? (
              filteredArticles.map((art) => (
                <div
                  key={art.id}
                  onClick={() => {
                    onSelectArticle(art.id);
                    onClose();
                  }}
                  className="p-3 rounded hover:bg-gray-100 transition-colors cursor-pointer group flex items-start justify-between"
                >
                  <div>
                    <span className="text-[10px] uppercase font-bold text-gray-500 block mb-0.5">
                      {art.section} • {art.date}
                    </span>
                    <h4 className="font-headline font-bold text-sm text-black group-hover:underline leading-snug">
                      {art.title}
                    </h4>
                    <span className="text-xs text-gray-500">By {art.author}</span>
                  </div>
                  <ArrowRight className="w-4 h-4 text-gray-400 group-hover:text-black group-hover:translate-x-1 transition-all shrink-0 mt-1" />
                </div>
              ))
            ) : (
              <div className="text-center py-8 text-gray-500 text-sm font-headline">
                No matching stories found for "{query}". Try searching for "Trump", "Ukraine", or "Polling".
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};
