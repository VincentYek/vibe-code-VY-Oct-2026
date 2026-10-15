import React, { useState } from 'react';
import { Article } from '../types/news';
import {
  ArrowLeft,
  Bookmark,
  Share2,
  Volume2,
  MessageSquare,
  Clock,
  Sparkles,
  Check,
  ZoomIn,
  ZoomOut,
} from 'lucide-react';

interface ArticleViewProps {
  article: Article;
  onBack: () => void;
  onSelectArticle: (articleId: string) => void;
  useWireframePhotos: boolean;
}

export const ArticleView: React.FC<ArticleViewProps> = ({
  article,
  onBack,
  onSelectArticle,
  useWireframePhotos,
}) => {
  const [fontSizeOffset, setFontSizeOffset] = useState<number>(0);
  const [isBookmarked, setIsBookmarked] = useState(false);
  const [copiedShare, setCopiedShare] = useState(false);
  const [isPlayingAudio, setIsPlayingAudio] = useState(false);
  const [comments, setComments] = useState<string[]>([
    'Crucial reporting on senior administrative turnover. The staffing implications for policy continuity cannot be overstated.',
    'A fascinating glimpse into the internal dynamics of cabinet leadership.',
  ]);
  const [newComment, setNewComment] = useState('');

  const handleShare = () => {
    navigator.clipboard?.writeText(window.location.href);
    setCopiedShare(true);
    setTimeout(() => setCopiedShare(false), 2000);
  };

  const handleAddComment = (e: React.FormEvent) => {
    e.preventDefault();
    if (!newComment.trim()) return;
    setComments([newComment.trim(), ...comments]);
    setNewComment('');
  };

  const getBodyFontSize = () => {
    if (fontSizeOffset === -1) return 'text-[17px] leading-relaxed';
    if (fontSizeOffset === 1) return 'text-[21px] leading-relaxed';
    return 'text-[19px] leading-relaxed';
  };

  return (
    <article className="w-full max-w-[960px] mx-auto px-4 py-4 antialiased">
      {/* Sticky Top Reader Action Bar */}
      <div className="sticky top-0 bg-white/95 backdrop-blur-xs border-b border-nyt py-2.5 mb-6 flex items-center justify-between z-20">
        <div className="flex items-center gap-3">
          <button
            onClick={onBack}
            className="flex items-center gap-1.5 text-xs font-semibold text-[#121212] hover:text-black hover:underline cursor-pointer group"
          >
            <ArrowLeft className="w-4 h-4 transition-transform group-hover:-translate-x-0.5" />
            <span>Front Page</span>
          </button>
          <span className="text-gray-300">|</span>
          <span className="text-xs uppercase tracking-wider font-bold text-gray-500">
            {article.section}
          </span>
        </div>

        {/* Reading Controls */}
        <div className="flex items-center gap-2 text-xs">
          {/* Text Size Adjuster */}
          <div className="hidden sm:flex items-center border border-gray-200 rounded px-1.5 py-0.5 bg-gray-50">
            <button
              onClick={() => setFontSizeOffset((prev) => Math.max(-1, prev - 1))}
              disabled={fontSizeOffset === -1}
              className="p-1 hover:text-black text-gray-500 disabled:opacity-30 cursor-pointer"
              title="Smaller font"
            >
              <ZoomOut className="w-3.5 h-3.5" />
            </button>
            <span className="text-[10px] font-bold px-1 text-gray-600">A</span>
            <button
              onClick={() => setFontSizeOffset((prev) => Math.min(1, prev + 1))}
              disabled={fontSizeOffset === 1}
              className="p-1 hover:text-black text-gray-500 disabled:opacity-30 cursor-pointer"
              title="Larger font"
            >
              <ZoomIn className="w-3.5 h-3.5" />
            </button>
          </div>

          {/* Bookmark */}
          <button
            onClick={() => setIsBookmarked(!isBookmarked)}
            className={`p-1.5 rounded border border-gray-200 transition-colors cursor-pointer flex items-center gap-1 ${
              isBookmarked ? 'bg-amber-50 text-amber-800 border-amber-300' : 'hover:bg-gray-50 text-gray-700'
            }`}
            title="Save article to Reading List"
          >
            <Bookmark className={`w-3.5 h-3.5 ${isBookmarked ? 'fill-current' : ''}`} />
            <span className="hidden md:inline text-[11px] font-medium">
              {isBookmarked ? 'Saved' : 'Save'}
            </span>
          </button>

          {/* Share */}
          <button
            onClick={handleShare}
            className="p-1.5 rounded border border-gray-200 hover:bg-gray-50 text-gray-700 transition-colors cursor-pointer flex items-center gap-1"
            title="Share article link"
          >
            {copiedShare ? (
              <Check className="w-3.5 h-3.5 text-green-600" />
            ) : (
              <Share2 className="w-3.5 h-3.5" />
            )}
            <span className="hidden md:inline text-[11px] font-medium">
              {copiedShare ? 'Copied' : 'Share'}
            </span>
          </button>
        </div>
      </div>

      {/* Main Article Header */}
      <header className="max-w-[720px] mx-auto mb-8">
        {article.categoryTag && (
          <div className="mb-2">
            <span
              className={`text-xs uppercase tracking-wider font-bold ${
                article.categoryTagColor || 'text-red-700'
              }`}
            >
              {article.categoryTag}
            </span>
          </div>
        )}

        <h1 className="font-headline text-3xl sm:text-5xl font-bold leading-[1.12] text-[#121212] mb-4">
          {article.title}
        </h1>

        <p className="font-headline text-xl sm:text-2xl text-[#444444] leading-snug mb-6 font-normal">
          {article.subtitle}
        </p>

        {/* Byline & Metadata */}
        <div className="flex flex-col sm:flex-row sm:items-center justify-between border-y border-nyt py-3 gap-2 text-xs text-[#666]">
          <div>
            <div className="font-semibold text-black text-sm">By {article.author}</div>
            {article.authorTitle && (
              <div className="text-[11px] text-gray-500">{article.authorTitle}</div>
            )}
          </div>
          <div className="flex items-center gap-3 text-[11px]">
            <span>{article.date}</span>
            <span>•</span>
            <span className="flex items-center gap-1">
              <Clock className="w-3 h-3 text-gray-400" />
              {article.readTime}
            </span>
          </div>
        </div>

        {/* Listen to Article Audio Bar */}
        <div className="mt-4 bg-gray-50 border border-nyt p-3 rounded flex items-center justify-between">
          <div className="flex items-center gap-3">
            <button
              onClick={() => setIsPlayingAudio(!isPlayingAudio)}
              className="w-8 h-8 rounded-full bg-black text-white flex items-center justify-center hover:bg-gray-800 transition-colors cursor-pointer"
            >
              <Volume2 className="w-4 h-4" />
            </button>
            <div>
              <div className="font-bold text-xs text-black">
                {isPlayingAudio ? 'Playing audio dispatch...' : 'Listen to this article'}
              </div>
              <div className="text-[10px] text-gray-500">Narrated by Times Voice • 4:20</div>
            </div>
          </div>
          <div className="w-32 bg-gray-200 h-1.5 rounded-full overflow-hidden hidden sm:block">
            <div
              className={`bg-black h-full transition-all duration-500 ${
                isPlayingAudio ? 'w-1/2 animate-pulse' : 'w-0'
              }`}
            />
          </div>
        </div>
      </header>

      {/* Featured Image */}
      {article.imageUrl && (
        <figure className="max-w-[840px] mx-auto mb-8">
          {useWireframePhotos ? (
            <div className="w-full h-[380px] bg-[#ebe7f5] border border-gray-200 flex flex-col items-center justify-center">
              <div className="w-20 h-20 text-[#7c6bb5]">
                <svg className="w-full h-full" viewBox="0 0 48 48" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <rect x="4" y="6" width="40" height="36" rx="4" />
                  <circle cx="16" cy="18" r="4" fill="currentColor" />
                  <path d="M4 36l12-14 10 12 8-8 10 10" />
                </svg>
              </div>
            </div>
          ) : (
            <div className="w-full max-h-[500px] overflow-hidden bg-gray-100 border border-gray-200">
              <img
                src={article.imageUrl}
                alt={article.title}
                className="w-full h-full object-cover object-center"
              />
            </div>
          )}
          {article.imageCaption && (
            <figcaption className="text-xs text-[#666] mt-2 flex flex-col sm:flex-row sm:justify-between border-b border-nyt pb-2">
              <span>{article.imageCaption}</span>
              <span className="text-gray-400">{article.imageCredit}</span>
            </figcaption>
          )}
        </figure>
      )}

      {/* Body Content */}
      <div className="max-w-[720px] mx-auto">
        <div className={`font-headline ${getBodyFontSize()} text-[#222222] space-y-6`}>
          {article.body.map((paragraph, index) => (
            <p
              key={index}
              className={index === 0 ? 'drop-cap font-normal' : 'font-normal'}
            >
              {paragraph}
            </p>
          ))}
        </div>

        {/* Key Takeaways Box */}
        <div className="my-8 bg-[#f9f9f9] border-l-4 border-black p-4 rounded-r">
          <h4 className="font-sans-editorial text-xs font-bold uppercase tracking-widest text-[#121212] mb-2 flex items-center gap-1.5">
            <Sparkles className="w-3.5 h-3.5 text-black" />
            Key Context
          </h4>
          <ul className="text-sm font-headline text-gray-700 space-y-2 list-disc list-inside">
            <li>Federal transitions typically see reorganization, but current departures focus heavily on institutional policy stewards.</li>
            <li>Congressional oversight hearings on federal workforce adjustments are slated for the upcoming committee session.</li>
          </ul>
        </div>

        {/* Comments Section */}
        <section className="mt-12 pt-8 border-t-2 border-black">
          <div className="flex items-center justify-between mb-6">
            <div className="flex items-center gap-2">
              <MessageSquare className="w-5 h-5 text-black" />
              <h3 className="font-sans-editorial font-bold text-lg text-black">
                Comments ({comments.length + (article.commentsCount || 0)})
              </h3>
            </div>
            <span className="text-xs text-gray-500 font-sans-editorial">
              Moderated by The New York Times
            </span>
          </div>

          <form onSubmit={handleAddComment} className="mb-6">
            <textarea
              value={newComment}
              onChange={(e) => setNewComment(e.target.value)}
              placeholder="What are your thoughts on this story? (Civility required)..."
              rows={3}
              className="w-full p-3 border border-nyt rounded text-sm focus:outline-none focus:border-black font-headline transition-colors"
            />
            <div className="flex justify-end mt-2">
              <button
                type="submit"
                disabled={!newComment.trim()}
                className="bg-black hover:bg-gray-800 disabled:opacity-40 text-white font-bold text-xs px-4 py-2 rounded transition-colors cursor-pointer"
              >
                Post Comment
              </button>
            </div>
          </form>

          <div className="space-y-4">
            {comments.map((comm, idx) => (
              <div key={idx} className="bg-gray-50 p-3.5 rounded border border-gray-100">
                <div className="flex justify-between items-center mb-1 text-xs">
                  <span className="font-bold text-black font-sans-editorial">Reader Dispatch</span>
                  <span className="text-gray-400">Just now</span>
                </div>
                <p className="font-headline text-sm text-[#333] leading-relaxed">{comm}</p>
              </div>
            ))}
          </div>
        </section>

        {/* Back to top button */}
        <div className="text-center py-10 border-t border-nyt mt-12">
          <button
            onClick={onBack}
            className="inline-flex items-center gap-2 border border-black hover:bg-black hover:text-white text-black font-semibold text-xs px-6 py-2.5 rounded-sm transition-colors cursor-pointer uppercase tracking-wider"
          >
            <ArrowLeft className="w-4 h-4" />
            <span>Return to Front Page</span>
          </button>
        </div>
      </div>
    </article>
  );
};
