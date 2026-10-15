import React from 'react';
import { Article } from '../types/news';
import { ImageIcon } from 'lucide-react';

interface LeadColumnProps {
  leadArticle: Article;
  secondArticle: Article;
  onSelectArticle: (articleId: string) => void;
  useWireframePhotos: boolean;
}

export const LeadColumn: React.FC<LeadColumnProps> = ({
  leadArticle,
  secondArticle,
  onSelectArticle,
  useWireframePhotos,
}) => {
  return (
    <section
      aria-labelledby="lead-story-heading"
      className="lg:col-span-5 lg:border-r border-nyt lg:pr-6"
    >
      {/* Primary Lead Story */}
      <article className="mb-6">
        <h2
          id="lead-story-heading"
          onClick={() => onSelectArticle(leadArticle.id)}
          className="font-headline text-3xl sm:text-4xl font-bold leading-tight hover:text-[#555] transition-colors cursor-pointer mb-2"
        >
          {leadArticle.title}
        </h2>
        <p
          onClick={() => onSelectArticle(leadArticle.id)}
          className="font-headline text-[17px] text-[#333] leading-snug mb-3 cursor-pointer"
        >
          {leadArticle.subtitle}
        </p>
        <div className="flex items-center text-[11px] text-[#727272] gap-2 mb-4">
          <span className="font-semibold text-black">By {leadArticle.author}</span>
          <span>•</span>
          <span>{leadArticle.readTime}</span>
        </div>

        {/* Lead Image */}
        <figure className="relative mb-3 group cursor-pointer" onClick={() => onSelectArticle(leadArticle.id)}>
          {useWireframePhotos ? (
            /* Exact match of prompt screenshot wireframe lavender box with image icon */
            <div className="w-full h-[280px] bg-[#ebe7f5] border border-gray-200 flex flex-col items-center justify-center transition-all group-hover:brightness-95">
              <div className="w-16 h-16 rounded bg-transparent flex items-center justify-center text-[#7c6bb5]">
                <svg className="w-14 h-14" viewBox="0 0 48 48" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <rect x="4" y="6" width="40" height="36" rx="4" />
                  <circle cx="16" cy="18" r="4" fill="currentColor" />
                  <path d="M4 36l12-14 10 12 8-8 10 10" />
                </svg>
              </div>
            </div>
          ) : (
            /* High-resolution editorial photo */
            <div className="w-full h-[280px] overflow-hidden bg-gray-100 border border-gray-100">
              <img
                src={leadArticle.imageUrl}
                alt="High ranking official leaving administration"
                className="w-full h-full object-cover object-top transition-transform duration-500 group-hover:scale-[1.02]"
              />
            </div>
          )}
          <figcaption className="text-[10px] text-[#666] mt-1.5 flex justify-between">
            <span>{leadArticle.imageCaption}</span>
            <span className="text-[#999]">{leadArticle.imageCredit}</span>
          </figcaption>
        </figure>

        {/* Associated Bullet Points */}
        <ul className="space-y-3 pt-2 text-[14px] font-headline border-t border-nyt">
          {leadArticle.bullets?.map((bullet, idx) => (
            <li key={idx} className="flex items-baseline gap-2">
              {bullet.tag ? (
                <span className="text-red-700 font-bold text-xs tracking-tight">{bullet.tag}</span>
              ) : (
                <span className="text-[#666] font-bold text-xs">•</span>
              )}
              <button
                onClick={() => bullet.linkedArticleId && onSelectArticle(bullet.linkedArticleId)}
                className="text-left hover:underline leading-snug text-[#121212] font-semibold cursor-pointer"
              >
                {bullet.text}
              </button>
            </li>
          ))}
        </ul>
      </article>

      {/* Secondary Story: White House Transition */}
      <div className="border-t border-nyt pt-5 mt-5">
        <article>
          <span className="text-[10px] uppercase tracking-wider font-bold text-[#b82218] block mb-1">
            {secondArticle.categoryTag}
          </span>
          <h3
            onClick={() => onSelectArticle(secondArticle.id)}
            className="font-headline text-2xl font-bold leading-snug hover:text-[#555] cursor-pointer mb-2"
          >
            {secondArticle.title}
          </h3>
          <p
            onClick={() => onSelectArticle(secondArticle.id)}
            className="font-headline text-[15px] text-[#444] leading-normal mb-2 cursor-pointer"
          >
            {secondArticle.subtitle}
          </p>
          <span className="text-[11px] text-[#727272]">{secondArticle.readTime}</span>
        </article>
      </div>
    </section>
  );
};
