import React from 'react';
import { X, FileText, Download, Printer } from 'lucide-react';
import { ARTICLES } from '../data/newsData';

interface TodaysPaperModalProps {
  onClose: () => void;
  onSelectArticle: (articleId: string) => void;
}

export const TodaysPaperModal: React.FC<TodaysPaperModalProps> = ({
  onClose,
  onSelectArticle,
}) => {
  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-2xl max-h-[85vh] overflow-hidden flex flex-col border border-gray-300">
        {/* Header */}
        <div className="border-b border-nyt p-4 flex items-center justify-between bg-gray-50 shrink-0">
          <div>
            <div className="font-masthead text-xl text-black">The New York Times</div>
            <div className="text-xs text-gray-600 font-bold">
              Today’s Paper • Wednesday, October 14, 2026 • Vol. CLXXV No. 60,942
            </div>
          </div>
          <button
            onClick={onClose}
            className="p-1 hover:bg-gray-200 rounded text-gray-500 hover:text-black cursor-pointer"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Content */}
        <div className="p-6 overflow-y-auto space-y-6">
          <div className="border-b-2 border-black pb-2 flex justify-between items-baseline">
            <h3 className="font-sans-editorial text-xs font-bold uppercase tracking-widest text-black">
              Section A: The Front Page & National
            </h3>
            <span className="text-[11px] text-gray-500">Page A1</span>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {ARTICLES.map((art) => (
              <div
                key={art.id}
                onClick={() => {
                  onSelectArticle(art.id);
                  onClose();
                }}
                className="p-3 border border-gray-200 rounded hover:border-black transition-colors cursor-pointer group bg-gray-50/50"
              >
                <span className="text-[10px] font-bold uppercase text-red-700 tracking-wider block mb-1">
                  {art.section} • {art.categoryTag || 'News'}
                </span>
                <h4 className="font-headline font-bold text-base leading-snug group-hover:underline text-black mb-1">
                  {art.title}
                </h4>
                <p className="font-headline text-xs text-gray-600 line-clamp-2">
                  {art.subtitle}
                </p>
                <div className="mt-2 text-[10px] text-gray-500 font-sans-editorial">
                  By {art.author}
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Footer */}
        <div className="p-3 bg-gray-50 border-t border-nyt flex items-center justify-between text-xs text-gray-600 shrink-0">
          <span>Replica Edition • Late Edition (East Coast)</span>
          <div className="flex gap-2">
            <button
              onClick={() => window.print()}
              className="flex items-center gap-1 px-3 py-1.5 rounded border border-gray-300 hover:bg-white text-gray-700 cursor-pointer text-xs"
            >
              <Printer className="w-3.5 h-3.5" />
              <span>Print Page 1</span>
            </button>
            <button
              onClick={onClose}
              className="bg-black hover:bg-gray-800 text-white font-bold px-4 py-1.5 rounded cursor-pointer text-xs"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
